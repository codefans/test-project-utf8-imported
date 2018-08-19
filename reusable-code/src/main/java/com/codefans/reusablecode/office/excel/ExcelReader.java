package com.codefans.reusablecode.office.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ShengzhiCai
 * @date: 2018-08-19 23:49
 */
public class ExcelReader {

    public static void main(String[] args) {
        xssfRead();
//        hssfRead();
    }

    public static void xssfRead() {

        String filePath = "/Users/caishengzhi/Downloads/math.xlsx";
        File excelFile = new File(filePath);
        if(!excelFile.exists()) {
            throw new IllegalArgumentException("文件:[" + filePath + "]不存在。");
        }
        try{

            List<String> weightList = new ArrayList<String>();
            List<String> priceList = new ArrayList<String>();
            String totalPrice = "";

            String weightColumnTitle = "";
            String priceColumnTitle = "";

            //读取xlsx文件
            XSSFWorkbook xssfWorkbook = null;
            //寻找目录读取文件
            InputStream is = new FileInputStream(excelFile);
            xssfWorkbook = new XSSFWorkbook(is);

            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            int columnCount = 3;

            ArrayList<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
            //遍历xlsx中的sheet
            for (int numSheet = 0; numSheet < numberOfSheets; numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // 对于每个sheet，读取其中的每一行
                for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow == null) continue;
                    ArrayList<String> curarr=new ArrayList<String>();
                    String cellTypeName = "";

                    for(int columnNum = 0 ; columnNum<columnCount ; columnNum++){
                        XSSFCell cell = xssfRow.getCell(columnNum);
    //                        System.out.println(cell);

                        cellTypeName = cell.getCellTypeEnum().name();
    //                        System.out.println(cellTypeName);
                        if("STRING".equalsIgnoreCase(cellTypeName)) {
                            System.out.println("STRING:" + cell.getStringCellValue());
                        } else if ("NUMERIC".equalsIgnoreCase(cellTypeName)) {
                            Double d = cell.getNumericCellValue();
                            DecimalFormat df = new DecimalFormat("#.##");
                            String value = df.format(d);
                            System.out.println("NUMERIC:" + value);
                        } else if ("FORMULA".equalsIgnoreCase(cellTypeName)) {
    //                            System.out.println("FORMULA:" + cell.getCellFormula());
                            System.out.println("FORMULA:" + cell.getCTCell());
                        } else {
                            System.out.println("UNKNOW:" + cell);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private static String getValue(XSSFCell xssfRow) {
        if(xssfRow==null){
            return "---";
        }
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            double cur=xssfRow.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if(Double.parseDouble(longVal + ".0") == cur)
                inputValue = longVal;
            else
                inputValue = cur;
            return String.valueOf(inputValue);
        } else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK || xssfRow.getCellType() == xssfRow.CELL_TYPE_ERROR){
            return "---";
        }
        else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    public static void hssfRead() {
        try {

            String filePath = "/Users/caishengzhi/Downloads/math.xlsx";
            File excelFile = new File(filePath);
            if(!excelFile.exists()) {
                throw new IllegalArgumentException("文件:[" + filePath + "]不存在。");
            }

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excelFile));
            HSSFSheet sheet = null;
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
                sheet = workbook.getSheetAt(i);
                for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
                    HSSFRow row = sheet.getRow(j);
                    if (row != null) {
                        for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum，是获取最后一个不为空的列是第几个
                            if (row.getCell(k) != null) { // getCell 获取单元格数据
                                System.out.print(row.getCell(k) + "\t");
                            } else {
                                System.out.print("\t");
                            }
                        }
                    }
                    System.out.println(""); // 读完一行后换行
                }
                System.out.println("读取sheet表：" + workbook.getSheetName(i) + " 完成");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
