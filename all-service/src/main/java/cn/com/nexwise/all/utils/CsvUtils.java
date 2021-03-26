package cn.com.nexwise.all.utils;

import cn.com.nexwise.all.annotation.ExcelField;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 导出CSV工具类
 *
 * */
public class CsvUtils {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    /**
     * 通用导出方法
     * @param rows 导出对象列表
     * @param headers 标题
     * @param  fileName 文件名称
     *
     * */
    public static <T> void export(HttpServletResponse response, List<T> rows, String[] headers, String fileName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try (OutputStream outputStream = response.getOutputStream()) {
            if (rows.isEmpty()) {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "application/html;charset=UTF-8");
                String str = "no data";
                outputStream.write(str.getBytes("UTF-8"));
                outputStream.flush();
                return;
            }
            // 生成临时文件
            File tempFile = File.createTempFile("temp", ".csv");
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
            fileName = fileName + dateFormat.format(new Date()) + "." + "csv";
            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headers);
            CSVPrinter csvPrinter = new CSVPrinter(outputStreamWriter, csvFormat);
            for (int i = 0; i < rows.size(); i++) {
                T t = rows.get(i);
                Class<?> clazz = t.getClass();
                Field[] fields = clazz.getDeclaredFields();
                // 每一行的列的值
                List<Object> records = new ArrayList<>();
                Object[] arr = new Object[headers.length];
                for (Field field : fields) {
                    // 根据反射获取导出字段
                    ExcelField annotation = field.getAnnotation(ExcelField.class);
                    if (annotation != null) {
                        String header = annotation.header();
                        if (StringUtils.isEmpty(header) || StringUtils.isBlank(header)) {
                            continue;
                        }
                        for (int j = 0; j < headers.length; j++) {
                            if (headers[j].equals(header)) { // 根据反射创建单元格
                                field.setAccessible(true);
                                Object value = field.get(t);
                                if (value instanceof String) {
                                    arr[j] = value;
                                } else if (value instanceof Integer || value instanceof Long) {
                                    arr[j] = value;
                                } else if (value instanceof Double) {
                                    arr[j] = value;
                                } else if (value instanceof Date) {
                                    String format = DateFormatUtils.format((Date) value, "yyyy-MM-dd HH:mm:ss");
                                    arr[j] = format;
                                } else if (value instanceof ByteArrayOutputStream) { //图片类型
                                    arr[j] = value;
                                }
                            }
                        }
                    }
                }
                for (Object o : arr) {
                    records.add(o);
                }
                if (!records.isEmpty()) { // 写入一行数据
                    csvPrinter.printRecord(records);
                }
            }
            csvPrinter.flush();
            csvPrinter.close();

            response.setCharacterEncoding("ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
            response.setHeader("Content-Type", "application/octet-stream");
            // 读取临时文件
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            byte[] bytes = new byte[1024];
            int n;
            while ((n = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, n);
            }
            fileInputStream.close();
            // 删除临时文件
            tempFile.delete();
            outputStream.flush();
        } catch (Exception e) {
            logger.error("导出CSV错误：{}", e);
            return;
        }
    }
}
