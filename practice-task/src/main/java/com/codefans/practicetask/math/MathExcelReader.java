package com.codefans.practicetask.math;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 必须按模板的样子组织excel数据
 * 1.共三列
 * 2.总价在最后一行的第三列的位置
 * @author: ShengzhiCai
 * @date: 2018-08-20 00:40
 */
public class MathExcelReader {

    private String filePath;

    public MathExcelReader(String filePath) {
        this.filePath = filePath;
    }

    List<String> weightList = new ArrayList<String>();
    List<String> priceList = new ArrayList<String>();
    String totalPrice = "";

    public static void main(String[] args) {
        String filePath = "/Users/caishengzhi/Downloads/math.xlsx";
        MathExcelReader mathExcelReader = new MathExcelReader(filePath);
        mathExcelReader.xssfRead();
        mathExcelReader.print();
    }

    public void xssfRead() {

        File excelFile = new File(filePath);
        if(!excelFile.exists()) {
            throw new IllegalArgumentException("文件:[" + filePath + "]不存在。");
        }
        try{

            String weightColumnTitle = "";
            String priceColumnTitle = "";

            //读取xlsx文件
            XSSFWorkbook xssfWorkbook = null;
            //寻找目录读取文件
            InputStream is = new FileInputStream(excelFile);
            xssfWorkbook = new XSSFWorkbook(is);

            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            int columnCount = 3;

            int weightColumnIndex = 0;
            int priceColumnIndex = 0;


            ArrayList<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
            //遍历xlsx中的sheet
            for (int numSheet = 0; numSheet < numberOfSheets; numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }

                int lastRowNum = xssfSheet.getLastRowNum();
                // 对于每个sheet，读取其中的每一行
                for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow == null) continue;
                    ArrayList<String> curarr=new ArrayList<String>();
                    String cellTypeName = "";
                    String cellValue = "";
                    CellType cellType = null;

                    for(int columnNum = 0 ; columnNum<columnCount ; columnNum++){
                        XSSFCell cell = xssfRow.getCell(columnNum);
                        //                        System.out.println(cell);

                        if(cell != null) {
                            cellType = cell.getCellTypeEnum();
                            if (cellType != null) {
                                cellTypeName = cellType.name();
                            }
                        }

                        //                        System.out.println(cellTypeName);
                        if("STRING".equalsIgnoreCase(cellTypeName)) {

                            if(rowNum == 0) {
                                cellValue = cell.getStringCellValue();
                                if(this.isPriceColumnTitle(cellValue)) {
                                    priceColumnIndex = columnNum;
                                } else if(this.isWeightColumnTitle(cellValue)) {
                                    weightColumnIndex = columnNum;
                                }
                            }

//                            System.out.println("STRING:" + cell.getStringCellValue());
                        } else if ("NUMERIC".equalsIgnoreCase(cellTypeName)) {
                            Double d = cell.getNumericCellValue();
                            DecimalFormat df = new DecimalFormat("#.##");
                            String value = df.format(d);
//                            System.out.println("NUMERIC:" + value);

                            if(columnNum == priceColumnIndex) {
                                priceList.add(value);
                            } else if(columnNum == weightColumnIndex) {
                                weightList.add(value);
                            } else if(rowNum == lastRowNum && columnNum == columnCount - 1) {
                                totalPrice = value;
                            }

                        } else if ("FORMULA".equalsIgnoreCase(cellTypeName)) {
                            //System.out.println("FORMULA:" + cell.getCellFormula());
//                            System.out.println("FORMULA:" + cell.getCTCell());
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

    public boolean isWeightColumnTitle(String name) {
        if("数量".equalsIgnoreCase(name)) {
            return true;
        } else if ("重量".equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

    public boolean isPriceColumnTitle(String name) {
        if("单价".equalsIgnoreCase(name)) {
            return true;
        } else if ("价格".equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

    public void print() {
        System.out.println("重量 单价");
        for(int i = 0; i < weightList.size();i ++) {
            System.out.println(weightList.get(i) + " " + priceList.get(i));
        }
        System.out.println("totalPrice:" + totalPrice);
    }

    public List<String> getWeightList() {
        return weightList;
    }

    public void setWeightList(List<String> weightList) {
        this.weightList = weightList;
    }

    public List<String> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<String> priceList) {
        this.priceList = priceList;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
