package com.dao;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liran on 27/08/17.
 */
public class XLSWriter
{
    public static void main(String[] args)
    {
        try
        {
            String excelPath = "Football.xls";

            FileOutputStream fileOutputStream = new FileOutputStream(new File(excelPath));

            // Create Workbook instance holding .xls file
            HSSFWorkbook workbook = new HSSFWorkbook();

            // Create a new Worksheet
            HSSFSheet sheet = workbook.createSheet("Footbal Players");

            Object[][] footballPlayers = {
                    {"sasasa\nwwwww pppppp","Country"},
                    {"Ronaldo","Portugal"},
                    {"Rooney","England"},
                    {"Roben","Netherland"},
                    {"Messi","Argentina"}
            };

            int rownum = 0;

            for(Object[] player : footballPlayers)
            {
                Row row = sheet.createRow(rownum++);

                int colnum = 0;
                for(Object value : player)
                {
                    Cell cell = row.createCell(colnum++);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    }
                }
            }
            sheet.addMergedRegion(new CellRangeAddress(
                    0, //first row (0-based)
                    1, //last row  (0-based)
                    0, //first column (0-based)
                    1  //last column  (0-based)
            ));
            CellStyle style = workbook.createCellStyle();
            Row row = sheet.getRow(0);
            CellUtil.setAlignment(row.getCell(0), HorizontalAlignment.CENTER);

            //Write workbook into the excel
            workbook.write(fileOutputStream);
            //Close the workbook
            workbook.close();

        } catch (IOException ie)
        {
            ie.printStackTrace();
        }
    }
}