package com.dao;


import com.dm.*;
import com.interfaces.UpdateDM;
import com.utils.CustomDate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static java.lang.String.format;

/**
 * Created by liran on 5/11/17.
 */
public class PatientModel{
    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();

    private PatientModel(){}

    private static PatientModel patientModelInstance;

    public static PatientModel getPatientModelInstance(){
        if(patientModelInstance == null)
            patientModelInstance = new PatientModel();
        return patientModelInstance;
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
//    private String buildReport(List<PatientRecord> i_PatientRecords){
//        StringBuilder buildPatientFile = new StringBuilder();
//        buildPatientFile.append(format("***********************************************Start-Report-FOR-The-Following-Patient ID: %s ***********************************************\n",i_PatientID));
//        buildPatientFile.append(format("%s\n",i_PatientRecords.toString()));
//        buildPatientFile.append(format("%s\n",getObjectListAsJsonList(i_PatientRecords)));
//        buildPatientFile.append(format("***********************************************End-Report-FOR-The-Following-Patient ID: %s *************************************************\n",i_PatientID));
//        return buildPatientFile.toString();
//    }


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
                     createAndInsertUpdateDmCell(p.getListOfActivityUpdate());
                     createAndInsertUpdateDmCell(p.getListOfHabitUpdate());
                     //TODO need to add itnterface only for getName to be more ploymorfizem
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
            int colAmount = 14;
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

            //Activity cell
            cellRangeAddresses.add(new CellRangeAddress(0,0,2,3));

            //Habits
            cellRangeAddresses.add(new CellRangeAddress(0,0,4,5));

            //Medicine
            cellRangeAddresses.add(new CellRangeAddress(0,1,6,6));

            //MoodCondition
            cellRangeAddresses.add(new CellRangeAddress(0,1,7,7));

            //SleepCondition
            cellRangeAddresses.add(new CellRangeAddress(0,0,8,10));
            cellRangeAddresses.forEach(this::mergeCells);

            row = sheet.getRow(0);
            row.getCell(0).setCellValue("Patient ID");
            row.getCell(1).setCellValue("Update date");
            row.getCell(2).setCellValue("Activity");
            row.getCell(4).setCellValue("Habits");
            row.getCell(6).setCellValue("Medicine");
            row.getCell(7).setCellValue("Mood condition");
            row.getCell(8).setCellValue("Sleep condition");

            //Get the second line that was created
            row = sheet.getRow(--rowNum);
            insertSubHeadLines(row, style);
            firstDataLine = ++rowNum;

        }

        private void mergeCells(CellRangeAddress cellRangeAddress){
            sheet.addMergedRegion(cellRangeAddress);
        }

        private void insertSubHeadLines(Row row, CellStyle style){
            String [] patientRecordSubHeadLines = {"Name", "Description","Name", "Description", "Quality","Hours","Disorder"};
            int column = 2;
            int headLinesSize = patientRecordSubHeadLines.length+2;
            while(column< headLinesSize){
                Cell cell = row.getCell(column);
                cell.setCellValue(patientRecordSubHeadLines[column-2]);
                cell.setCellStyle(style);
                column++;
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
                    int x =1;


                }
        }

         private void createAndInsertUpdateDmCell(Collection<? extends UpdateDM> updateDMS) {
             Row row;
             int rowItr,firstCol = 2, secondCol = 3;
             if(updateDMS instanceof HabitUpdate){
                 firstCol = 4;
                 secondCol = 5;
             }
             if (firstDataLine == rowNum) {
                 for (UpdateDM dm : updateDMS) {
                     row = sheet.createRow(rowNum++);
                     row.createCell(firstCol).setCellValue(dm.getName());
                     row.createCell(secondCol).setCellValue(dm.getDescription());
                 }
             }
             else
             {
                 rowItr = firstDataLine;
                 Iterator<? extends UpdateDM> updateDMIterator = updateDMS.iterator();
                 while(rowItr<rowNum && updateDMIterator.hasNext()){
                     row = sheet.getRow(rowItr++);
                     UpdateDM dm = updateDMIterator.next();
                     row.createCell(firstCol).setCellValue(dm.getName());
                     row.createCell(secondCol).setCellValue(dm.getDescription());
                 }
                 while(updateDMIterator.hasNext()){
                     row = sheet.createRow(rowNum++);
                     UpdateDM dm = updateDMIterator.next();
                     row.createCell(firstCol).setCellValue(dm.getName());
                     row.createCell(secondCol).setCellValue(dm.getDescription());
                 }
             }
         }


         private  void createAndInsertSleepConditionCell(SleepCondition sleepCondition) {
             Row row;
             if (firstDataLine == rowNum) {
                 row = sheet.createRow(rowNum++);
                 sleepCondition.getSleepDisorders().forEach(dis ->
                         sheet.createRow(rowNum++).createCell(7).setCellValue(dis.getSleepDisorderName()));
             }
             else
             {
                 row = sheet.getRow(firstDataLine);
                 Row localRow;
                 int rowItr = firstDataLine;
                 Iterator<SleepDisorder> sleepDisorderIterator = sleepCondition.getSleepDisorders().iterator();
                 while(rowItr<rowNum && sleepDisorderIterator.hasNext()){
                     localRow = sheet.getRow(rowItr++);
                     SleepDisorder dm = sleepDisorderIterator.next();
                     localRow.createCell(7).setCellValue(dm.getSleepDisorderName());
                 }
                 while(sleepDisorderIterator.hasNext()){
                     localRow = sheet.createRow(rowNum++);
                     SleepDisorder dm = sleepDisorderIterator.next();
                     localRow.createCell(7).setCellValue(dm.getSleepDisorderName());
                 }

             }
             colNum = 4;
             row.createCell(colNum++).setCellValue(sleepCondition.getSleepConditionName());
             row.createCell(colNum++).setCellValue(sleepCondition.getSleepQuality());
             row.createCell(colNum++).setCellValue(sleepCondition.getSleepHours());

         }
    }



    public static void main(String args[]){
        PatientModel patientModel = new PatientModel();
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
