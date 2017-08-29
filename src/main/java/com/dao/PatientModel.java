package com.dao;


import com.dm.*;
import com.utils.CustomDate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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

         public XSL(){
            patientRecordList = getAllPatientUpdates();
        }

         private Boolean isRowNumberAlreadyCreated(int rowNumber){
             return (sheet.getRow(rowNumber)==null);
         }

         public void createNewExcelReport(String filePath) {

             try {
                 workbook = new HSSFWorkbook();
                 sheet = workbook.createSheet(CustomDate.getDateFormat().format(new Date()));
                 createHeadLines();

                 patientRecordList.forEach(p-> createAndInsertActivityCell(p.getListOfActivityUpdate()));
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
            String[] patientRecordHeadLines = {"Patient ID", "Update date", "Activity", "Habits", "Medicine", "Mood condition","Sleep condition"};
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

            for(int i=0;i<patientRecordHeadLines.length;i++){
                sheet.getRow(0).getCell(i).setCellValue(patientRecordHeadLines[i]);

            }
            //Get the second line that was created
            row = sheet.getRow(--rowNum);
            insertSubHeadLines(row, style);

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
        private <T> void createAndInsertSingleCell(Row row, int column, T data){
            Cell cell = row.createCell(column);
            if(data instanceof String)
                cell.setCellValue((String) data);
            else if(data instanceof Date)
                cell.setCellValue((Date) data);

        }

         private  void createAndInsertActivityCell(Collection<ActivityUpdate> activityUpdates){
            Row row;
             for(ActivityUpdate act :activityUpdates){
                 //if(sheet.getRow(++rowNum))
                 row = sheet.createRow(++rowNum);
                 row.createCell(2).setCellValue(act.getActivityName());
                 row.createCell(3).setCellValue(act.getActivityDescription());
             }
         }

         private  void createAndInsertHabitCell(Row row, Collection<HabitUpdate> habitUpdates){
             colNum = 3;
             habitUpdates.forEach(habit -> {
                 row.createCell(colNum++).setCellValue(habit.getHabitName());
                 row.createCell(colNum).setCellValue(habit.getHabitDescription());
             });
         }

         private  void createAndInsertSleepConditionCell(Row row, SleepCondition sleepCondition){
             colNum = 4;
             row.createCell(colNum++).setCellValue(sleepCondition.getSleepConditionName());
             row.createCell(colNum++).setCellValue(sleepCondition.getSleepQuality());
             row.createCell(colNum++).setCellValue(sleepCondition.getSleepHours());
             sleepCondition.getSleepDisorders().forEach(dis->
                 sheet.createRow(rowNum++).createCell(colNum).setCellValue(dis.getSleepDisorderName()));
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
