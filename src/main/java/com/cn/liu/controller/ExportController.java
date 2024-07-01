package com.cn.liu.controller;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 测试
 *
 * @Author lxw
 * @Date 2022-04-05 17:54
 **/
@RestController
@RequestMapping("/demo")
public class ExportController {

    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal("123.456");
        BigDecimal bd2 = new BigDecimal("0.00123");
        BigDecimal bd3 = new BigDecimal("123");
        BigDecimal bd4 = new BigDecimal("0.000");


        System.out.println("bd1的小数位数: " + bd1.scale()); // 输出: bd1的小数位数: 3
        System.out.println("bd2的小数位数: " + bd2.scale()); // 输出: bd2的小数位数: 5，注意这里原数是0.00123，显示的小数位是5，因为是从0开始计数
        System.out.println("bd3的小数位数: " + bd3.scale()); // 输出: bd3的小数位数: 0，表示这是一个整数
        System.out.println("bd4的小数位数: " + bd4.scale());

        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < bd4.scale(); i++) {
            sb.append("0");
        }
        System.out.println(sb.toString());
    }

    public static void main2(String[] args) {
        try {
            // 创建一个新的Excel工作簿
            Workbook workbook = new XSSFWorkbook();

            // 创建一个新的工作表
            Sheet sheet = workbook.createSheet("数据表");

            // 创建标题行
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("序号");
            headerRow.createCell(1).setCellValue("姓名");
            headerRow.createCell(2).setCellValue("体重");

            // 设置单元格样式以显示两位小数
            CellStyle style = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            style.setDataFormat(format.getFormat("0.000000")); // 应用数字格式

            // 填充数据行
            Row dataRow1 = sheet.createRow(1);
            dataRow1.createCell(0).setCellValue(1);
            dataRow1.createCell(1).setCellValue("张三");
//            dataRow1.createCell(2).setCellValue(65.00);
            Cell cell12 = dataRow1.createCell(2);
            cell12.setCellStyle(style); // 将样式应用于单元格
            cell12.setCellValue(66.00);

            Row dataRow2 = sheet.createRow(2);
            dataRow2.createCell(0).setCellValue(2);
            dataRow2.createCell(1).setCellValue("李四");
//            dataRow2.createCell(2).setCellValue(71.667);
            Cell cell22 = dataRow2.createCell(2);
            cell22.setCellStyle(style); // 将样式应用于单元格
            cell22.setCellValue(71.667);

            // 自动调整列宽
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            // 写入文件
            try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\44674\\Desktop\\output.xlsx")) {
                workbook.write(outputStream);
                System.out.println("Excel文件已成功导出！");
            }

            // 关闭工作簿
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


