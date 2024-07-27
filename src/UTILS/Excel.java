package UTILS;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    public static XSSFWorkbook xuatFileNhanVien(String header[], JTable tblTable, String title){
        XSSFWorkbook book = new XSSFWorkbook();
        try {
            XSSFSheet sheet = book.createSheet();
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length-1));
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(title);
            cell.setCellStyle(titleFormat(book));
            row = sheet.createRow(2);
            for (int i=0;i<header.length;i++){
                cell = row.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, true));
            }
            int rownum = 3;
            int stt = 1;
            int count = 0;
            if (tblTable.getSelectedRows().length>0)
                for (int i : tblTable.getSelectedRows()){
                    row = sheet.createRow(rownum++);
                    cell = row.createCell(0);
                    cell.setCellValue((int) stt++);
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(1);
                    cell.setCellValue((String) tblTable.getValueAt(i, 1));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(2);
                    cell.setCellValue((String) tblTable.getValueAt(i, 2));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    cell = row.createCell(3);
                    cell.setCellValue((String) tblTable.getValueAt(i, 3));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    cell = row.createCell(4);
                    cell.setCellValue((String) tblTable.getValueAt(i, 4));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(5);
                    cell.setCellValue((String) tblTable.getValueAt(i, 5));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    count++;
                }
            else
                for (int i=0;i<tblTable.getRowCount();i++){
                    row = sheet.createRow(rownum++);
                    cell = row.createCell(0);
                    cell.setCellValue((int) stt++);
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(1);
                    cell.setCellValue((String) tblTable.getValueAt(i, 1));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(2);
                    cell.setCellValue((String) tblTable.getValueAt(i, 2));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    cell = row.createCell(3);
                    cell.setCellValue((String) tblTable.getValueAt(i, 3));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    cell = row.createCell(4);
                    cell.setCellValue((String) tblTable.getValueAt(i, 4));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
                    cell = row.createCell(5);
                    cell.setCellValue((String) tblTable.getValueAt(i, 5));
                    cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
                    count++;
                }
            for (int i=0;i<header.length;i++){
                sheet.autoSizeColumn(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }
    public static List<Object[]> readFile(JTable tblTable, File file, String header[]){
        List<Object[]> list = new ArrayList<>(); 
        try {
            XSSFWorkbook book = new XSSFWorkbook(file.getPath());
            XSSFSheet sheet = book.getSheetAt(0);
            Iterator<Row> itr = sheet.rowIterator();
            int rownum = 3, count = 1;
            boolean check = false;
            while (itr.hasNext()){
                Row row = itr.next();
                if (row.getRowNum()>=3){
                    Object[] obj = new Object[header.length];
                    obj[0] = count;
                    for (int i=1;i<header.length;i++){  
                        Cell cell = row.getCell(i);
                        if (cell.getCellType()==CellType.STRING){
                            obj[i] = cell.getStringCellValue();
                        }
                        else if (cell.getCellType()==CellType.NUMERIC){
                            obj[i] = cell.getNumericCellValue();
                        }
                        else{
                            obj[i] = cell.getDateCellValue();
                        }
                    }
                    list.add(obj);
                    count++;
                }
            }   
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void exportRows(XSSFWorkbook book, Row row, XSSFSheet sheet, String header[], int rownum, Cell cell, JTable tblTable, int stt, int i){
        cell = row.createCell(0);
        cell.setCellValue((int) stt++);
        cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
        row = sheet.createRow(rownum++);
        for (int j=0;j<header.length;j++){
            cell = row.createCell(j+1); 
            cell.setCellValue((String) tblTable.getValueAt(i, j));
            cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
        }
//        cell = row.createCell(1);
//        cell.setCellValue((String) tblTable.getValueAt(i, 1));
//        cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
//        cell = row.createCell(2);
//        cell.setCellValue((String) tblTable.getValueAt(i, 2));
//        cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
//        cell = row.createCell(3);
//        cell.setCellValue((String) tblTable.getValueAt(i, 3));
//        cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
//        cell = row.createCell(4);
//        cell.setCellValue((String) tblTable.getValueAt(i, 4));
//        cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.CENTER, false));
//        cell = row.createCell(5);
//        cell.setCellValue((String) tblTable.getValueAt(i, 5));
//        cell.setCellStyle(cellFormat(book, BorderStyle.THIN, HorizontalAlignment.LEFT, false));
    }
    public static CellStyle titleFormat(XSSFWorkbook book){
        XSSFCellStyle style = book.createCellStyle();
        Font font = book.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        font.setFontHeightInPoints((short) 15);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
    public static CellStyle cellFormat(XSSFWorkbook book, BorderStyle border, HorizontalAlignment align, boolean bold){
        XSSFCellStyle style = book.createCellStyle();
        Font font = book.createFont();
        font.setFontName("Arial");
        font.setBold(bold);
        style.setFont(font);
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderBottom(border);
        style.setBorderRight(border);
        style.setAlignment(align); 
        return style;
    }
}
