package com.virtusa;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.io.FileInputStream;
import java.util.ArrayList;

//public class ReadAndWriteData {
    public class ReadAndWriteData {

    public static XSSFSheet workSheet;
    public static XSSFWorkbook workBook;

    @Test
    public void readWriteData(){
        try{
            //Read data from XLSX file- store in ArrayList and print to console log
            ReadAndWriteData readAndWriteData=new ReadAndWriteData();
            ArrayList<ArrayList<String>> retVal = readAndWriteData.getExcelData(System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelData.xlsx", "Sheet1");
//            for(int i=0;i<retVal.size();i++){

            for (ArrayList<String> arrVal : retVal) {
                for (int j = 0; j < arrVal.size(); j++) {
                    if (j == arrVal.size() - 1) {
                        System.out.print(arrVal.get(j));
                    } else {
                        System.out.print(arrVal.get(j) + ", ");
                    }
                }
                System.out.println();
            }

        }catch (Exception e){
            Assertions.fail("readWriteData :- Exception :- "+e);
        }

    }


    public ArrayList<ArrayList<String>> getExcelData(String Path, String SheetName){
        ArrayList<ArrayList<String>> objArrayList = null;
        try{
//            ArrayList<ArrayList<String>> objArrayList=new ArrayList<ArrayList<String>>();
            FileInputStream ExcelFile=new FileInputStream(Path) ;
            workBook=new XSSFWorkbook(ExcelFile);
            workSheet=workBook.getSheet(SheetName);

            int numOfRows=workSheet.getLastRowNum();
            int numOfColumns = workSheet.getRow(0).getLastCellNum();
//            objArrayList= new ArrayList<ArrayList<String>>(numOfRows);
            objArrayList= new ArrayList<>(numOfRows);


            for(int i=0;i<=numOfRows;i++){
//                objArrayList.add(new ArrayList<String>());
                objArrayList.add(new ArrayList<>());

                for(int j=0;j<numOfColumns;j++){
                    String val;
                    if(workSheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC){
                        val=workSheet.getRow(i).getCell(j).toString();
                    }else{
                        val=workSheet.getRow(i).getCell(j).getStringCellValue();
                    }
                    objArrayList.get(i).add(val);
                }
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return objArrayList;
    }

}
