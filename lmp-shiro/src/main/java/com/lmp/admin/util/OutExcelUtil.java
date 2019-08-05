
package com.lmp.admin.util;

import com.jackpot.base.base.BaseException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * java导出excel表格
 */
public class OutExcelUtil {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 导出excel表格
     *
     * @param linkedHashMap | key    dataList中T中的字段名
     *                      | value  excel列名
     * @param dataList      导出哪个表的数据
     * @param outputStream  输出流
     */
    public static <T> void createExcel(LinkedHashMap<String, String> linkedHashMap, List<T> dataList, OutputStream outputStream) {
        // 创建Excel的工作书册 Workbook,对应到一个excel文档
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建Excel的工作sheet,对应到一个excel文档的tab
        XSSFSheet sheet = wb.createSheet("sheet1");
        // 第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;
        // 创建n个Excel的单元格 (n列)
        Set<Entry<String, String>> entrySet = linkedHashMap.entrySet();
        int k = 0;
        for (Entry<String, String> entry : entrySet) {
            sheet.setColumnWidth(k, 4500);// 设置单元格宽度
            String value = entry.getValue();
            cell = row.createCell(k);// 创建第 i+1列
            cell.setCellValue(value); // 设置列里的标题
            k++;
        }
        if (dataList != null && !dataList.isEmpty()) {
            // 第二行开始 循环数据库里的数据
            for (int i = 0; i < dataList.size(); i++) {
                XSSFRow row2 = sheet.createRow(i + 1);
                T mdl = dataList.get(i);
                Class<? extends Object> clazz = mdl.getClass();
                Set<String> keySet = linkedHashMap.keySet();
                int j = 0;
                for (String key : keySet) {
                    cell = row2.createCell(j);
                    if (key.equals("EXCELFORMULA")) {
                        String formula = "C" + (i + 2) + "-D" + (i + 2);
                        cell.setCellFormula(formula);
                    } else {
                        String upperCase = key.substring(0, 1).toUpperCase();
                        String getMethodName = "get" + upperCase + key.substring(1);
                        try {
                            Method getMethod = clazz.getMethod(getMethodName);
                            Object obj = getMethod.invoke(mdl);
                            setCellValue(cell, obj);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                            throw new BaseException(e.getMessage(), e);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            throw new BaseException(e.getMessage(), e);
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                            throw new BaseException(e.getMessage(), e);
                        }
                    }
                    j++;
                }
            }
        }
        try {
            wb.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(e.getMessage(), e);
        } finally {
            try {
                wb.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new BaseException(e.getMessage(), e);
            }
        }
    }

    private static void setCellValue(XSSFCell cell, Object obj) {
        if (null == obj) {
            cell.setCellValue("");
        } else if (obj instanceof Integer) {
            cell.setCellValue(((Integer) obj));
        } else if (obj instanceof String) {
            cell.setCellValue((String) obj);
        } else if (obj instanceof Date) {
            String dateString = formatter.format((Date) obj);
            cell.setCellValue(dateString);
        } else if (obj instanceof Boolean) {
            cell.setCellValue(obj.toString());
        } else if (obj instanceof Double) {
            cell.setCellValue((Double) obj);
        } else if (obj instanceof BigDecimal) {
            cell.setCellValue(String.valueOf(obj));
        } else if (obj instanceof Long) {
            cell.setCellValue(String.valueOf(obj));
        } else {
            cell.setCellValue(obj.toString());
        }
    }

    /**
     * 将数据导出到模板，再下载文件
     *
     * @param tplPath      模板文件路径
     * @param fieldArr    需要导出的字段名
     * @param dataList     数据列表
     * @param outputStream 输入流
     * @param <T>
     * @return
     */
    public static <T> void createFromTemplate(String tplPath, String[] fieldArr, List<T> dataList, OutputStream outputStream) {
        OPCPackage pkg = null;
        XSSFWorkbook book = null;
        try {
            try {
                pkg = OPCPackage.open(tplPath);
            } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
                e.printStackTrace();
            }
            book = new XSSFWorkbook(pkg);
            XSSFSheet sheet = book.getSheet("Sheet1");
            for (int i = 0; i < dataList.size(); i++) {
                //创建一行
                XSSFRow row = sheet.createRow(i + 3);

                T mdl = dataList.get(i);
                Class<? extends Object> clazz = mdl.getClass();
                for (int j = 0; j < fieldArr.length; j++) {
                    String key = fieldArr[j];
                    String upperCase = key.substring(0, 1).toUpperCase();
                    String getMethodName = "get" + upperCase + key.substring(1);
                    Method getMethod = clazz.getMethod(getMethodName);
                    Object obj = getMethod.invoke(mdl);
                    //创建一个单元格
                    XSSFCell cell = row.createCell(j);
                    setCellValue(cell, obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new BaseException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BaseException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new BaseException(e.getMessage(), e);
        } finally {
            try {
                if (null != book) {
                    book.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new BaseException(e.getMessage(), e);
            }
        }
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null)
            return cellValue;
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue().trim();
                break;
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BLANK:
                cellValue = "";
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                byte[] bytes = {cell.getErrorCellValue()};
                cellValue = new String(bytes);
                break;
            default:
                cellValue = cell.getStringCellValue().trim();
                break;
        }
        return cellValue;
    }


}
