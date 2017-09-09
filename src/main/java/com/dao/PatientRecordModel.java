package com.dao;

import com.dm.*;
import com.dm.updateDM.ActivityUpdate;
import com.dm.updateDM.HabitUpdate;
import com.dm.updateDM.MedicineUpdate;
import com.dm.updateDM.SleepDisorderUpdate;
import com.interfaces.UpdateDM;
import com.interfaces.UpdateDMProxy;
import com.utils.CustomDate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.*;
import org.hibernate.Session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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
            session.save(i_PatientRecord);
            transaction.commit();
            session.close();
            return i_PatientRecord.toString();
        } finally {
            if(session!=null)
                session.close();
        }
    }

    private Optional<SleepCondition> addSleepConditionIfExist(PatientRecord i_PatientRecord, Session i_Session){
        if (i_PatientRecord.getSleepConditionAndDisorder() != null) {
            SleepConditionAndDisorder value = i_PatientRecord.getSleepConditionAndDisorder();
            SleepCondition sleepCondition = new SleepCondition(value.getSleepHours(),value.getSleepQuality());
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

    public List<PatientRecord> getAllPatientUpdates(){
        List<PatientRecord> patientRecords = new ArrayList<>();
        Collection<Patient> patients =  modelGenerics.findAllByClass(Patient.class);
        patients.forEach(p -> patientRecords.addAll(getAllUpdatesByPatientID(p.getPatientID())));
        return patientRecords;
    }

    public List<PatientRecord> getAllUpdatesByPatientID(String i_PatientID) throws HibernateException{
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<PatientRecord> patientRecords = session.createQuery(format("select p from PATIENT_RECORD as p where p.patientID=%s",i_PatientID)).list();
            transaction.commit();
            return patientRecords;
        } finally {
            if(session!=null)
                session.close();
        }
    }

    private class XSL{
        private List<PatientRecord> patientRecordList;
        private Workbook workbook;
        private Sheet sheet;
        private int rowNum = 0;
        private int colNum = 0;
        private int firstDataLine;

        private XSL(){
            patientRecordList = getAllPatientUpdates();
        }

        private Boolean isRowNumberAlreadyCreated(int rowNumber){
            return (sheet.getRow(rowNumber)==null);
        }

        private void createNewExcelReport(String filePath) {

            try {
                workbook = new HSSFWorkbook();
                sheet = workbook.createSheet(CustomDate.getDateFormat().format(new Date()));
                createHeadLines();

                patientRecordList.forEach(p-> {
                    createAndInsertSingleCell(0, p.getPatientID());
                    createAndInsertSingleCell(1, p.getPatientLastUpdate());
                    createAndInsertDmCell(p.getListOfActivityUpdate());
                    createAndInsertDmCell(p.getListOfHabitUpdate());
                    createAndInsertDmCell(p.getListOfMedicineUpdate());
                    //createAndInsertSleepConditionCell(p.getSleepConditionAndDisorder());

                    firstDataLine = rowNum;
                });

                try {
                    //Write the workbook to the file system
                    FileOutputStream out = new FileOutputStream(new File(filePath));
                    workbook.write(out);
                    out.close();
                    System.out.println("CountriesDetails.xlsx has been created successfully");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    workbook.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        private void createHeadLines(){
            int colAmount = 15;
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

        //support the following fields from PatientRecord: ID, Date, Medicine, MoodCondition
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
                int x =1;//TODO need to fix the style issue of DATE


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
            }
            if (firstDataLine == rowNum) {
                for (UpdateDM updateDm : dmList) {
                    row = sheet.createRow(rowNum++);
                    row.createCell(firstCol).setCellValue(updateDm.getName());
                    if(updateDm instanceof UpdateDMProxy) {
                        row.createCell(secondCol).setCellValue(((UpdateDMProxy) updateDm).getDescription());
                    }
                }
            }
            else
            {
                rowItr = firstDataLine;
                Iterator<? extends UpdateDM> dmIterator = dmList.iterator();
                while(rowItr<rowNum && dmIterator.hasNext()){
                    row = sheet.getRow(rowItr++);
                    UpdateDM updateDm = dmIterator.next();
                    row.createCell(firstCol).setCellValue(updateDm.getName());
                    if(updateDm instanceof UpdateDMProxy){
                        row.createCell(secondCol).setCellValue(((UpdateDMProxy) updateDm).getDescription());
                    }

                }
                while(dmIterator.hasNext()){
                    row = sheet.createRow(rowNum++);
                    UpdateDM updateDm = dmIterator.next();
                    row.createCell(firstCol).setCellValue(updateDm.getName());
                    if(updateDm instanceof UpdateDMProxy){
                        row.createCell(secondCol).setCellValue(((UpdateDMProxy) updateDm).getDescription());
                    }

                }
            }
        }


        private  void createAndInsertSleepConditionCell(SleepConditionAndDisorder sleepConditionAndDisorder) {
            Row row;
            int disorderColumn = 11;
            if (firstDataLine == rowNum) {
                row = sheet.createRow(rowNum++);
                sleepConditionAndDisorder.getSleepDisorders().forEach(dis ->
                        sheet.createRow(rowNum++).createCell(disorderColumn).setCellValue(dis.getName()));
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
                    localRow.createCell(disorderColumn).setCellValue(dm.getName());
                }
                while(sleepDisorderIterator.hasNext()){
                    localRow = sheet.createRow(rowNum++);
                    SleepDisorderUpdate dm = sleepDisorderIterator.next();
                    localRow.createCell(disorderColumn).setCellValue(dm.getName());
                }

            }
            colNum = 4;
            row.createCell(colNum++).setCellValue(sleepConditionAndDisorder.getSleepConditionName());
            row.createCell(colNum++).setCellValue(sleepConditionAndDisorder.getSleepQuality());
            row.createCell(colNum++).setCellValue(sleepConditionAndDisorder.getSleepHours());

        }
    }



    public static void main(String args[]){
        PatientRecordModel patientModel = new PatientRecordModel();
//        patientModel.getAllUpdatesByPatientID("1");
//        Link link = new Link();
//        link.setLinkHeadLine("news");
//        link.setLinkURL("url of news");
//        Link link2 = new Link();
//        link2.setLinkHeadLine("news2");
//        link2.setLinkURL("url of news2");
//        ModelGenerics.getModelGenericsInstance().addObjectToDB(link);
//        ModelGenerics.getModelGenericsInstance().addObjectToDB(link2);
        //patientModel.getAllUpdatesByPatientID("1")
//        List<PatientRecord> list =patientModel.getAllUpdatesByPatientID("1");
//        for(PatientRecord l:list){
//            for(ActivityUpdate w:l.getListOfActivityUpdate()){
//                System.out.println(w.getActivityName());
//            }
//        }
//        Collection<String> a = new ArrayList<>();
//        Collection<String> b = new ArrayList<>();
//        b.add("1");
//        b.add("2");
//        b.add("3");
//        Collection<String>c = new ArrayList<>();
//        c.add("5");
//        c.add("6");
//        c.add("7");
//        a.addAll(b);
//        a.addAll(c);
//        for(String w : a){
//            System.out.println(w);
//        }
       XSL xsl = patientModel.new XSL();
       xsl.createNewExcelReport("test.xlsx");






    }
}
