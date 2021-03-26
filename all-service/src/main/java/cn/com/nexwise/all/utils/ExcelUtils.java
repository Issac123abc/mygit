package cn.com.nexwise.all.utils;

import cn.com.nexwise.all.annotation.ExcelField;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @securityor skywin
 * @since Oct 8, 2015 10:49:37 AM
 */
public class ExcelUtils {

    /**
     * Logger
     */
    private final static Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 单元格样式
     */
    public static CellStyle getCommonCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * 通用导出方法
     * @param rows 导出对象列表
     * @param headers 标题
     * @param  fileName 文件名称
     *
     * */
    public static <T> void export(HttpServletResponse response, List<T> rows, String[] headers, String fileName){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try(OutputStream outputStream = response.getOutputStream()) {
            if (rows.isEmpty()) {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "application/html;charset=UTF-8");
                String str = "no data";
                outputStream.write(str.getBytes("UTF-8"));
                outputStream.flush();
                return;
            }
            fileName = fileName + dateFormat.format(new Date()) + "." + "xlsx";
            //加载到文件流getCommonCellStyle
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            HSSFCell headerCell = null;
            HSSFRichTextString text = null;
            CellStyle dataCellStyle = getCommonCellStyle(workbook);;

            //设置标题行
            HSSFRow row = sheet.createRow(0);
            for(int i = 0; i < headers.length; i ++){
                headerCell = row.createCell(i);
                text = new HSSFRichTextString(headers[i]);
                headerCell.setCellValue(text);
                headerCell.setCellStyle(dataCellStyle);
            }
            for(int i = 0 ; i < rows.size(); i ++){
                row = sheet.createRow(i + 1); // 行
                T t = rows.get(i);
                Class<?> clazz = t.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    ExcelField annotation = field.getAnnotation(ExcelField.class);
                    if (annotation != null){
                        String header = annotation.header();
                        if (StringUtils.isEmpty(header) || StringUtils.isBlank(header)) continue;
                        for (int j = 0; j < headers.length; j++) {
                            if (headers[j].equals(header)) { // 根据反射创建单元格
                                HSSFCell cell = row.createCell(j);
                                cell.setCellStyle(dataCellStyle); // 设置单元格式
                                sheet.setColumnWidth(j, 3000); // 设置单元格最小长度
                                field.setAccessible(true);
                                Object value = field.get(t);
                                if (value instanceof String) {
                                    cell.setCellValue(String.valueOf(value));
                                } else if (value instanceof Integer || value instanceof Long) {
                                    cell.setCellValue((Integer) value);
                                } else if (value instanceof Double) {
                                    cell.setCellValue((Double) value);
                                } else if (value instanceof Date) {
                                    String format = DateFormatUtils.format((Date) value, "yyyy-MM-dd HH:mm:ss");
                                    cell.setCellValue(format);
                                } else if (value instanceof ByteArrayOutputStream) { //图片类型
                                    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                                    ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) value;
                                    //anchor主要用于设置图片的属性
                                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 30, 700, 255, (short) j, i + 1, (short) j, i + 1);
                                    //插入图片
                                    patriarch.createPicture(anchor, workbook.addPicture(byteArrayOutputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                                    row.setHeight((short)2000);
                                    sheet.setColumnWidth(j, 5000);
                                }
                            }
                        }
                    }
                }
            }
            response.setCharacterEncoding("ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Content-Type", "application/octet-stream");
            workbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("excel导出错误：" + e);
            return ;
        }
    }

}
