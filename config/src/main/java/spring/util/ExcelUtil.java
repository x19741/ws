package spring.util;

import lombok.Data;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.*;

@Data
public class ExcelUtil {


    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }


    public static List<List<String>> parseExcel(Sheet sheet) {

        List<List<String>> llist = new ArrayList<>();
        //遍历行row
        for (int rownum = 0; rownum <= sheet.getLastRowNum(); rownum++) {
            Row sheetRow = sheet.getRow(rownum);
            if (sheetRow == null) {
                continue;
            }
            //遍历列cell
            List<String> slist = new ArrayList<>();
            for (int cellnum = 0; cellnum <= sheetRow.getLastCellNum(); cellnum++) {
                Cell cell = sheetRow.getCell(cellnum);
                /*if(cell==null){
                    continue;
                }*/
                slist.add(getValue(cell));
                System.out.print(" " + getValue(cell));
            }
            llist.add(slist);
            System.out.println();
        }
        return llist;
    }

    /**
     * 静态
     *
     * @param cell
     * @return
     */
    private static String getValue(Cell cell) {
        if (cell == null) {
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
    }


    public static HSSFWorkbook generateExcel() {
        return new HSSFWorkbook();
    }

    public static void export(HSSFWorkbook wb, String fileName, HttpServletResponse response) {
        try {
            if (fileName == null || "".equals(fileName))
                fileName = "excel";
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static HSSFWorkbook generateSheet(HSSFWorkbook wb, String sheetName, String[] fields, List<List<String>> list) {

        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 居中


        //设置表头字段名
        HSSFCell cell;
        int m = 0;
        for (String fieldName : fields) {
            cell = row.createCell(m);
            cell.setCellValue(fieldName);
            cell.setCellStyle(style);

            m++;
        }


        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);

            for (int j = 0; j < list.get(i).size(); j++){
                row.createCell(j).setCellValue(list.get(i).get(j));
            }

        }

        return wb;
    }


}
