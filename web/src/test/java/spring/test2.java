package spring;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.service.*;
import spring.util.JsonUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.Cell.*;


public class test2 {


    public static void main(String[] args) throws Exception {
        String dictype="用户";
        int i=dictype.indexOf("用户") ;
        System.out.println(i);
        if(dictype!=null&&dictype.indexOf("用户")>0){

        }

        /*String fileName = "D:\\ChuangZhi\\创致表格\\过程文档\\20200407-南航各部门统计表【附对接人员分工-投诉项目问题】.xlsx";
        InputStream is=new FileInputStream(fileName);


        //Workbook wb=new Workbook(fs);//创建了一个工作簿
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        Workbook wb=null;
        Exceltion.getWorkbook(is,fileType);
        ExcelUtil
        JsonUtil.sucess("2",null);

        Map<String,List<List<String>>>  map=new HashMap<>();
        //for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {

            Sheet sheet=wb.getSheetAt(2); //获取第一个sheet页
            if(sheet==null){
                return;
            }
            List<List<String>> llist=Exception.parseExcel(sheet);
            map.put(sheet.getSheetName(),llist);
        //}
        //数据库导入

*/

    }











   /* @Test
    public void usetText(){
        Long startdate=System.currentTimeMillis();
        try{




            String fileName = "D:\\ChuangZhi\\创致表格\\过程文档\\20200407-南航各部门统计表【附对接人员分工-投诉项目问题】.xlsx";
            Workbook workbook = null;
            FileInputStream inputStream = null;

            try {
                // 获取Excel后缀名
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                // 获取Excel文件
                File excelFile = new File(fileName);
                if (!excelFile.exists()) {
                    System.out.println("指定的Excel文件不存在！");
                    return;
                }

                // 获取Excel工作簿
                inputStream = new FileInputStream(excelFile);
                workbook = getWorkbook(inputStream, fileType);

                // 读取excel中的数据
                List<Object> resultDataList = parseExcel(workbook);
                System.out.println(JSON.toJSONString(resultDataList,SerializerFeature.WriteMapNullValue));
            } catch (Exception e) {
                System.out.println("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
                return ;
            } finally {
                try {
                    if (null != workbook) {
                        workbook.close();
                    }
                    if (null != inputStream) {
                        inputStream.close();
                    }
                } catch (Exception e) {
                    System.out.println("关闭数据流出错！错误信息：" + e.getMessage());
                    return ;
                }
            }

            System.out.println(System.currentTimeMillis()-startdate);
        }catch (Exception e){
            e.printStackTrace();
        }


    }









    *//**
     * 解析Excel数据
     * @param workbook Excel工作簿对象
     * @return 解析结果
     *//*
    private static  List<Object> parseExcel(Workbook workbook) {
        List<Object> resultDataList = new ArrayList<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                System.out.println("解析Excel失败，在第一行没有读取到任何数据！");
            }

            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (null == row) {
                    continue;
                }

                Map<String,String> resultData = convertRowToData(row);
                if (null == resultData) {
                    System.out.println("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                resultDataList.add(resultData);
            }
        }

        return resultDataList;
    }


    *//**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     *
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     *//*
    private static Map<String,String> convertRowToData(Row row) {
        Map<String,String> smap=new HashMap<>();


        for (int cellNum=0;cellNum<row.getRowNum();cellNum++){
            Cell cell = row.getCell(cellNum++);
            String name = convertCellValueToString(cell);
            smap.put(String.valueOf(cellNum),name);
        }
        return smap;
    }


    *//**
     * 将单元格内容转换为字符串
     * @param cell
     * @return
     *//*
    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;

        switch (cell.getCellType()) {
            case CELL_TYPE_NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case CELL_TYPE_STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case CELL_TYPE_BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case CELL_TYPE_BLANK:     // 空值
                break;
            case CELL_TYPE_FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case CELL_TYPE_ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }*/
}
