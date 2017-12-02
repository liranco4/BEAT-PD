package com.dao;

import com.dm.*;
import com.dm.updateDM.*;
import com.interfaces.UpdateDM;
import com.interfaces.UpdateDMProxy;
import com.utils.CustomDate;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.*;
import org.hibernate.Session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.utils.SingleLogger.LOGGER;
import static java.lang.String.format;

/**
 * Created by liran on 5/11/17.
 */
public class PatientRecordModel {

    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();
    private static PatientRecordModel patientRecordModelInstance;

    private PatientRecordModel(){}

    public static PatientRecordModel getPatientRecordModelInstance(){
        if(patientRecordModelInstance == null)
            patientRecordModelInstance = new PatientRecordModel();
        return patientRecordModelInstance;
    }

    public String addPatientRecord(PatientRecord i_PatientRecord)throws HibernateException{
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, i_PatientRecord.getPatientID());
            if (patient == null)
                throw new IllegalArgumentException(format("The following patientID:%s doesn't exist", i_PatientRecord.getPatientID()));

            final Session finalSession = session;
            //Save sleep condition object on SLEEP_CONDITION table
            addSleepConditionIfExist(i_PatientRecord,session).ifPresent(sleepCondition ->{
                    i_PatientRecord.setSleepCondition(sleepCondition);
                    //Save sleepDisorder update list on SLEEP_DISORDER_UPDATE table
                    addUpdateDMIfExist(i_PatientRecord.getSleepConditionAndDisorder().getSleepDisorders(),finalSession).ifPresent((idList)->
                            i_PatientRecord.setListOfSleepDisorderUpdate((List<SleepDisorderUpdate>) idList));
            });


            //Save activity update list on ACTIVITY_UPDATE table
            addUpdateDMIfExist(i_PatientRecord.getListOfActivityUpdate(),session).ifPresent((idList)->i_PatientRecord.setListOfActivityUpdate((List<ActivityUpdate>) idList));

            //Save habit update list on HABIT_UPDATE table
            addUpdateDMIfExist(i_PatientRecord.getListOfHabitUpdate(),session).ifPresent((idList)->i_PatientRecord.setListOfHabitUpdate((List<HabitUpdate>) idList));

            //Save Medicine update list on MEDICINE_Update table
            addUpdateDMIfExist(i_PatientRecord.getListOfMedicineUpdate(),session).ifPresent((idList)->i_PatientRecord.setListOfMedicineUpdate((List<MedicineUpdate>) idList));

            //Save MoodCondition update list on MoodCondition table
            addUpdateDMIfExist(i_PatientRecord.getListOfMoodCondition(),session).ifPresent((idList)->i_PatientRecord.setListOfMoodCondition((List<MoodConditionUpdate>) idList));


            session.save(i_PatientRecord);
            transaction.commit();
            session.close();
            return i_PatientRecord.toString();
        } finally {
            if(session!=null)
                session.close();
        }
    }

    private Optional<SleepConditionUpdate> addSleepConditionIfExist(PatientRecord i_PatientRecord, Session i_Session){
        if (i_PatientRecord.getSleepConditionAndDisorder() != null) {
            SleepConditionAndDisorder value = i_PatientRecord.getSleepConditionAndDisorder();
            SleepConditionUpdate sleepCondition = new SleepConditionUpdate(value.getSleepHours(),value.getSleepQuality());
            i_Session.save(sleepCondition);
            return Optional.of(sleepCondition);
        }
        return Optional.empty();
    }

    private Optional<List<? extends UpdateDM>> addUpdateDMIfExist(List<? extends UpdateDM> updateDMList, Session i_Session){
        if (!updateDMList.isEmpty()) {
            List<UpdateDM> resultList = new ArrayList<>();
            for (UpdateDM updateDM: updateDMList) {
                i_Session.save(updateDM);
                resultList.add(updateDM);
            }
            return Optional.of(resultList);
        }
        return Optional.empty();
    }

    private List<PatientRecord> getAllPatientUpdates(){
        List<PatientRecord> patientRecords = new ArrayList<>();
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<String> patientIDRecorded = session.createQuery(format("select p.patientID from PATIENT_RECORD as p group by p.patientID")).getResultList();
            transaction.commit();
            patientIDRecorded.forEach(p -> patientRecords.addAll(getAllUpdatesByPatientID(p)));
            return patientRecords;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    private List<PatientRecord> getAllUpdatesByPatientID(String i_PatientID) throws HibernateException{
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<PatientRecord> patientRecords = session.createQuery(format("select p from PATIENT_RECORD as p where p.patientID=%s", i_PatientID)).getResultList();
            transaction.commit();
            return patientRecords;

        } finally {
            if(session!=null)
                session.close();
        }
    }

    public void createNewExcelReport(String filePath, Optional<String> patientID){
        XSL xsl = new XSL();
        xsl.createNewExcelReport(filePath,patientID);
    }

    private class XSL {
        private List<PatientRecord> patientRecordList;
        private Workbook workbook;
        private Sheet sheet;
        private int rowNum = 0;
        private int colNum = 0;
        private int firstDataLine;

        private XSL() {
        }

        private Boolean isRowNumberAlreadyCreated(int rowNumber) {
            return (sheet.getRow(rowNumber) == null);
        }

        private void createNewExcelReport(String filePath, Optional<String> patientID) {

            try {
               if(patientID.isPresent()) {
                   String patientIDStr = patientID.get();
                   patientRecordList =  PatientRecordModel.getPatientRecordModelInstance().getAllUpdatesByPatientID(patientIDStr);
                   if(patientRecordList.isEmpty())
                       throw new IllegalArgumentException(format("Error the following patient: %s has not updates in the system",patientIDStr));
               }else
                 patientRecordList =  PatientRecordModel.getPatientRecordModelInstance().getAllPatientUpdates();
                workbook = new HSSFWorkbook();
                sheet = workbook.createSheet(CustomDate.getDateFormat().format(new Date()));
                createHeadLines();
                CellStyle underlineStyle = workbook.createCellStyle();
                underlineStyle.setBorderBottom(BorderStyle.MEDIUM);

                patientRecordList.forEach(p-> {
                    createAndInsertSingleCell(0, p.getPatientID());
                    createAndInsertSingleCell(1, p.getPatientLastUpdate());
                    createAndInsertDmCell(p.getListOfMoodCondition());
                    createAndInsertDmCell(p.getListOfActivityUpdate());
                    createAndInsertDmCell(p.getListOfHabitUpdate());
                    createAndInsertDmCell(p.getListOfMedicineUpdate());
                    SleepConditionUpdate sleepConditionUpdate = p.getSleepConditionUpdate();
                    if(sleepConditionUpdate != null)
                        createAndInsertDmCell(Collections.singletonList(sleepConditionUpdate));
                    createAndInsertDmCell(p.getSleepDisorderUpdate());
                    Row row = sheet.createRow(rowNum++);
                    row.setRowStyle(underlineStyle);
                    //row.getCell(row.getLastCellNum()-1).setCellStyle(underlineStyle);

                    firstDataLine = rowNum;
                });

                try {
                    //Write the workbook to the file system
                    FileOutputStream out = new FileOutputStream(new File(filePath));
                    workbook.write(out);
                    out.close();
                    LOGGER.log(Level.INFO,format("%s.xlsx has been created successfully",filePath));
                } catch (Exception e) {
                    LOGGER.log(Level.INFO,"error on write xlsx file");
                    e.printStackTrace();
                } finally {
                    workbook.close();
                }
            }
            catch (IOException e){
                LOGGER.log(Level.INFO,"error start create xlsx file");
                e.printStackTrace();
            }
        }

        private void createHeadLines(){
            int colAmount = 12;
            int rowAmount = 2;
            Row row;
            CellStyle style;
            // Creating a font
            Font font= workbook.createFont();
            font.setFontHeightInPoints((short)10);
            font.setFontName("Arial");
            font.setBold(true);
            font.setItalic(false);
            style=workbook.createCellStyle();;
            style.setAlignment(HorizontalAlignment.CENTER);
            // Setting font to style
            style.setFont(font);
            style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
            style.setFillPattern(FillPatternType.BIG_SPOTS);


            while(rowNum < rowAmount){
                row = sheet.createRow(rowNum);
                //row.setRowStyle(style);
                while(colNum < colAmount) {
                    Cell cell = row.createCell(colNum++);
                    if (rowNum == 0) {
                        cell.setCellStyle(style);
                    }
                    //sheet.autoSizeColumn(colNum-1);
                }
                colNum = 0;
                rowNum++;
            }
            colNum = 0;
            //edit excel view
            Collection<CellRangeAddress> cellRangeAddresses = new ArrayList<>();
            //ID cell
            cellRangeAddresses.add(new CellRangeAddress(0,1,0,0));
            //DATE cell
            cellRangeAddresses.add(new CellRangeAddress(0,1,1,1));

            //MoodCondition
            cellRangeAddresses.add(new CellRangeAddress(0,1,2,2));

            //Activity cell
            cellRangeAddresses.add(new CellRangeAddress(0,0,3,4));

            //Habits
            cellRangeAddresses.add(new CellRangeAddress(0,0,5,6));

            //Medicine
            cellRangeAddresses.add(new CellRangeAddress(0,0,7,8));

            //SleepConditionAndDisorder
            cellRangeAddresses.add(new CellRangeAddress(0,0,9,11));
            cellRangeAddresses.forEach(this::mergeCells);

            row = sheet.getRow(0);
            row.getCell(0).setCellValue("Patient ID");
            row.getCell(1).setCellValue("Update date");
            row.getCell(2).setCellValue("Mood condition");
            row.getCell(3).setCellValue("Activity");
            row.getCell(5).setCellValue("Habits");
            row.getCell(7).setCellValue("Medicine");
            row.getCell(9).setCellValue("Sleep condition");

            //Get the second line that was created
            row = sheet.getRow(--rowNum);
            insertSubHeadLines(row, style);
            firstDataLine = ++rowNum;


        }

        private void mergeCells(CellRangeAddress cellRangeAddress){
            sheet.addMergedRegion(cellRangeAddress);
        }

        private void insertSubHeadLines(Row row, CellStyle style){
            String [] patientRecordSubHeadLines = {"Name", "Description","Name", "Description", "Name", "Description","Quality","Hours","Disorder"};
            int indexColumn = 3;
            int headLinesSize = patientRecordSubHeadLines.length+indexColumn;
            while(indexColumn< headLinesSize){
                Cell cell = row.getCell(indexColumn);
                cell.setCellValue(patientRecordSubHeadLines[indexColumn-3]);
                cell.setCellStyle(style);
                indexColumn++;
            }
        }

        //support the following fields from PatientRecord: ID, Date
        private <T> void createAndInsertSingleCell( int column, T data){
            Row row;
            if(firstDataLine == rowNum) {
                row = sheet.createRow(rowNum++);
            }
            else
            {
                row = sheet.getRow(firstDataLine);
            }
            Cell cell = row.createCell(column);
            if (data instanceof String)
                cell.setCellValue((String) data);
            else if (data instanceof Date) {
                CellStyle cellStyle = workbook.createCellStyle();
                CreationHelper createHelper = workbook.getCreationHelper();
                cellStyle.setDataFormat(
                        createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
                cell = row.createCell(1);
                cell.setCellValue((Date) data);
                cell.setCellStyle(cellStyle);
            }

        }

        private void createAndInsertDmCell(List<? extends UpdateDM> dmList) {
            Row row;
            int rowItr,firstCol = 3, secondCol = 4;
            if(!dmList.isEmpty() && dmList.get(0) instanceof HabitUpdate){
                firstCol = 5;
                secondCol = 6;
            }
            else if(!dmList.isEmpty() && dmList.get(0) instanceof MedicineUpdate){
                firstCol = 7;
                secondCol = 8;
            }else if(!dmList.isEmpty() && dmList.get(0) instanceof MoodConditionUpdate){
                firstCol = 2;
            }else if(!dmList.isEmpty() && dmList.get(0) instanceof SleepDisorderUpdate){
                firstCol = 11;
            }else if (!dmList.isEmpty() && dmList.get(0) instanceof SleepConditionUpdate){
                firstCol = 9;
                secondCol = 10;
            }
            if (firstDataLine == rowNum) {
                for (UpdateDM updateDm : dmList) {
                    row = sheet.createRow(rowNum++);
                    if (updateDm.getFirstDetail() instanceof String) {
                        row.createCell(firstCol).setCellValue((String)updateDm.getFirstDetail());
                    }
                    else if(updateDm.getFirstDetail() instanceof Long){
                        row.createCell(firstCol).setCellValue((Long)updateDm.getFirstDetail());
                    }
                    if(updateDm instanceof UpdateDMProxy){
                        UpdateDMProxy updateDMProxy = ((UpdateDMProxy) updateDm);
                        if (updateDMProxy.getSecondDetail() instanceof String) {
                            row.createCell(secondCol).setCellValue((String)updateDMProxy.getSecondDetail());
                        }
                        else if(updateDMProxy.getSecondDetail() instanceof Long){
                            row.createCell(secondCol).setCellValue((Long)updateDMProxy.getSecondDetail());
                        }
                    }
                }
            }
            else
            {
                rowItr = firstDataLine;
                Iterator<? extends UpdateDM> dmIterator = dmList.iterator();
                while(rowItr<rowNum && dmIterator.hasNext()){
                    UpdateDM updateDm = dmIterator.next();
                    //if(updateDm!=null) {
                        row = sheet.getRow(rowItr++);
                        if (updateDm.getFirstDetail() instanceof String) {
                            row.createCell(firstCol).setCellValue((String) updateDm.getFirstDetail());
                        } else if (updateDm.getFirstDetail() instanceof Long) {
                            row.createCell(firstCol).setCellValue((Long) updateDm.getFirstDetail());
                        }
                        if (updateDm instanceof UpdateDMProxy) {
                            UpdateDMProxy updateDMProxy = ((UpdateDMProxy) updateDm);
                            if (updateDMProxy.getSecondDetail() instanceof String) {
                                row.createCell(secondCol).setCellValue((String) updateDMProxy.getSecondDetail());
                            } else if (updateDMProxy.getSecondDetail() instanceof Long) {
                                row.createCell(secondCol).setCellValue((Long) updateDMProxy.getSecondDetail());
                            }
                        }
                    //}
                }
                while(dmIterator.hasNext()){

                    UpdateDM updateDm = dmIterator.next();
                    //if(updateDm!=null) {
                        row = sheet.createRow(rowNum++);
                        if (updateDm.getFirstDetail() instanceof String) {
                            row.createCell(firstCol).setCellValue((String) updateDm.getFirstDetail());
                        } else if (updateDm.getFirstDetail() instanceof Long) {
                            row.createCell(firstCol).setCellValue((Long) updateDm.getFirstDetail());
                        }
                        if (updateDm instanceof UpdateDMProxy) {
                            UpdateDMProxy updateDMProxy = ((UpdateDMProxy) updateDm);
                            if (updateDMProxy.getSecondDetail() instanceof String) {
                                row.createCell(secondCol).setCellValue((String) updateDMProxy.getSecondDetail());
                            } else if (updateDMProxy.getSecondDetail() instanceof Long) {
                                row.createCell(secondCol).setCellValue((Long) updateDMProxy.getSecondDetail());
                            }
                        }
                    //}

                }
            }
        }


        private  void createAndInsertSleepConditionCell(SleepConditionAndDisorder sleepConditionAndDisorder) {
            Row row;
            int disorderColumn = 11;
            if (firstDataLine == rowNum) {
                row = sheet.createRow(rowNum++);
                sleepConditionAndDisorder.getSleepDisorders().forEach(dis ->
                        sheet.createRow(rowNum++).createCell(disorderColumn).setCellValue(dis.getFirstDetail()));
            }
            else
            {
                row = sheet.getRow(firstDataLine);
                Row localRow;
                int rowItr = firstDataLine;
                Iterator<SleepDisorderUpdate> sleepDisorderIterator = sleepConditionAndDisorder.getSleepDisorders().iterator();
                while(rowItr<rowNum && sleepDisorderIterator.hasNext()){
                    localRow = sheet.getRow(rowItr++);
                    SleepDisorderUpdate dm = sleepDisorderIterator.next();
                    localRow.createCell(disorderColumn).setCellValue(dm.getFirstDetail());
                }
                while(sleepDisorderIterator.hasNext()){
                    localRow = sheet.createRow(rowNum++);
                    SleepDisorderUpdate dm = sleepDisorderIterator.next();
                    localRow.createCell(disorderColumn).setCellValue(dm.getFirstDetail());
                }

            }
            colNum = 4;
            row.createCell(colNum++).setCellValue(sleepConditionAndDisorder.getSleepConditionName());
            row.createCell(colNum++).setCellValue(sleepConditionAndDisorder.getSleepQuality());
            row.createCell(colNum++).setCellValue(sleepConditionAndDisorder.getSleepHours());

        }
    }
}
