package ncu.stu.util;

import ncu.stu.beans.Attendees;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelUtils {
    //第一步，创建一个webbook,对应一个Excel文件
    public HSSFWorkbook generateExcel() {
        return new HSSFWorkbook();
    }

    public HSSFWorkbook generateSheet(HSSFWorkbook wb, String sheetName,
                                      String[] fields, List<Attendees> list) {
        //第二步，在webbook中添加一个sheet，对应Excel中的一个sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        //第三步，在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        //第四步，设置单元格格式，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置表头字段名
        HSSFCell cell = null;
        int m = 0;
        for (String fieldName : fields) {
            cell = row.createCell(m);
            cell.setCellValue(fieldName);
            m++;
        }
        //创建单元格，并设置值
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            Attendees attendee = list.get(i);
            row.createCell(0).setCellValue(attendee.getName());
            row.createCell(1).setCellValue(attendee.getWorkplace());
            row.createCell(2).setCellValue(attendee.getIdentify_id());
            row.createCell(3).setCellValue(attendee.getPhone());
            row.createCell(4).setCellValue(attendee.getMeetTime());
            row.createCell(5).setCellValue(attendee.getSex());
            row.createCell(6).setCellValue(attendee.getRoom());
        }
        return wb;
    }

    //第六步，实现文件下载保存
    public void export(HSSFWorkbook wb, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "注册信息表" + System.currentTimeMillis() + ".xls";
        try {
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1) + ".xls");
            wb.write(response.getOutputStream());
            // 如果转化为字节流：
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);
            byte[] bytes = os.toByteArray();
            // 获取响应报文输出流对象
            ServletOutputStream out = response.getOutputStream();
            // 输出
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("导出失败");
        }

    }
}


