package com.dao;


import com.dm.*;
import com.utils.CustomDate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
//        for (Patient patient: patients) {
//            patientRecords.addAll(getAllUpdatesByPatientID(patient.getPatientID()));
//        }
        patients.forEach(p -> patientRecords.addAll(getAllUpdatesByPatientID(p.getPatientID())));
        return patientRecords;
    }

    public List<PatientRecord> getAllUpdatesByPatientID(String i_PatientID) throws HibernateException{
        Session session = null;
        try {
            session = modelGenerics.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<PatientRecord> patientRecords = session.createQuery(format("select distinct p from PATIENT_RECORD as p join p.listOfActivityUpdate with p.patientID=%s",i_PatientID)).list();
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

         public XSL(String filePath){
            patientRecordList = getAllPatientUpdates();
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet(CustomDate.getDateFormat().format(new Date()));
        }

        private void createHeadLines(int colAmount, int rowAmount){
            String[] patienRecordHeadLines = {"Patient ID", "Update date", "Activity", "Habits", "Medicine", "Mood condition","Sleep condition"};
            Collection<Cell> subHeadLinesCells = new ArrayList<>();
            int i = 0;
            while(rowNum < rowAmount){
                 Row row = sheet.createRow(rowNum);
                 while(colNum < colAmount) {
                     Cell cell = row.createCell(colNum++);
                     switch (rowNum) {
                         case 0: {cell.setCellValue(patienRecordHeadLines[i++]);break;}
                         case 1: {subHeadLinesCells.add(cell); break;}
                     }
                 }
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
        }

        private void mergeCells(CellRangeAddress cellRangeAddress){
            sheet.addMergedRegion(cellRangeAddress);
        }

        private void insertSubHeadLines(){
            Row row = sheet.createRow(rowNum);
            //TODO need complete subHeadLines
        }

        //support ID, Date, Medicine, MoodCondition
        private <T> void createAndInsertSingleCell(Row row, int column, T data){
            Cell cell = row.createCell(column);
            if(data instanceof String)
                cell.setCellValue((String) data);
            else if(data instanceof Date)
                cell.setCellValue((Date) data);

        }

         private  void createAndInsertActivityCell(Row row, Collection<ActivityUpdate> activityUpdates){
             colNum = 2;
             activityUpdates.forEach(act -> {
                 row.createCell(colNum++).setCellValue(act.getActivityName());
                 row.createCell(colNum).setCellValue(act.getActivityDescription());
             });
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
             sleepCondition.getSleepDisorders().forEach(dis->row.createCell(colNum++).setCellValue(dis.getSleepDisorderName()));

         }

         public boolean createNewExcelReport() {

             return true;
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
    }
}
