package cn.xyr.ssm.common.utils;


import cn.xyr.ssm.common.utils.enumtype.ReportFileEnum;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * ReportUtil
 * 导出excel表格
 *
 * @author A
 */
public class ReportUtil {


    /**
     * @param name     表名
     * @param objList  导出的数据list
     */
    public static <T> void createExecl(String name, List<T> objList, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = objList.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        String[] columnNames = new String[fields.length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = fields[i].getName();
        }
        createExecl(name, objList, columnNames, columnNames, request, response);
    }

    /**
     *
     * @param reportFileEnum    报表名称和报表内容标题枚举
     * @param objList   导出的记录list
     * @param <T>   记录对象
     */
    public static <T> void createExecl(ReportFileEnum reportFileEnum, List<T> objList, HttpServletRequest request, HttpServletResponse response) throws Exception {
        createExecl(reportFileEnum.getFilename(),objList,reportFileEnum.getTitleNames(),reportFileEnum.getParams(),request,response);
    }

    /**
     * @param name        表名
     * @param objList     要导出的记录list
     * @param columnNames 列名
     * @param paramNames  列参数名(记录对应的参数名)
     * @param <T>         记录对象
     * @throws Exception
     */
    public static <T> void createExecl(String name, List<T> objList, String[] columnNames, String[] paramNames,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (CollectionUtils.isEmpty(objList)) {
            return;
        }
        String fileName = name+".xlsx";
        byte[] bytes = request.getHeader("User-Agent").contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
        //各类浏览器支持 iso 编码
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", new String(bytes, "ISO-8859-1")));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");

        //构建可容大数据量的excel文档
        //每1000行则输出
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        //使用gzip压缩，减小空间占用
        wb.setCompressTempFiles(true);
        //当前页数
        int sheetNum = 1;
        Sheet sheet = wb.createSheet("第" + sheetNum + "页");
        //构建标题栏

        Class clazz = objList.get(0).getClass();
        Row row0 = sheet.createRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            Cell cellHeader = row0.createCell(i);
            cellHeader.setCellValue(columnNames[i]);
        }
        //构建内容
        for (int i = 0; i < objList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Object obj = objList.get(i);
            for (int j = 0; j < paramNames.length; j++) {
                PropertyDescriptor pd = new PropertyDescriptor(paramNames[j], clazz);
                Cell cell = row.createCell(j);
                Method method = pd.getReadMethod();
                String value = "";
                if (method != null) {
                    try {
                        value = method.invoke(obj).toString();
                    } catch (Exception e) {
                        System.err.println("执行"+method.getName()+"的get方法时出错");
                    }
                }
                cell.setCellValue(value);
            }
        }
        //输出excel
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
        wb.dispose();
    }
}
