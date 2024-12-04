package com.cn.liu.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.cn.liu.entity.ImportRequest;
import com.cn.liu.listener.TestImportListener;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    /**
     * EasyExcel数据导入
     * 支持多sheet页
     */
    @GetMapping("/importtest")
    public void importtest() {
        try {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\44674\\Desktop\\test\\导入1.xls"));
            byte[] byteArray = IOUtils.toByteArray(inputStream);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            TestImportListener testImportListener = new TestImportListener();
            // 读取所有sheet页
            EasyExcel.read(byteArrayInputStream, ImportRequest.class, testImportListener).doReadAll();

            List<ImportRequest> dataList = testImportListener.getDataList();

            /*List<ImportRequest> sheet1 = new ArrayList<>();
            List<ImportRequest> sheet2 = new ArrayList<>();
            List<ImportRequest> sheet3 = new ArrayList<>();
            for (ImportRequest importRequest : dataList) {
                if ("1".equals(importRequest.getType())) {
                    sheet1.add(importRequest);
                }
                if ("2".equals(importRequest.getType())) {
                    sheet2.add(importRequest);
                }
                if ("3".equals(importRequest.getType())) {
                    sheet3.add(importRequest);
                }
            }

            System.out.println(sheet1.size());
            System.out.println(sheet2.size());
            System.out.println(sheet3.size());
            System.out.println(dataList.size());*/

            byteArrayInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * EasyExcel数据导出
     * 支持多sheet页
     */
    @GetMapping("/exporttest")
    public void exporttest() {
        List<ImportRequest> dataList1 = new ArrayList<>();
        ImportRequest data1 = new ImportRequest();
        data1.setProjectName("测试测试项目111");
        data1.setAddress("测试测试地址111");
        dataList1.add(data1);

        List<ImportRequest> dataList2 = new ArrayList<>();
        ImportRequest data2 = new ImportRequest();
        data2.setProjectName("测试测试项目222");
        data2.setAddress("测试测试地址222");
        dataList2.add(data2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (OutputStream os = new BufferedOutputStream(baos);
             OutputStream os2 = new BufferedOutputStream(baos)) {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接使用获取对象本身不要使用getOutputStream这个方法
            EasyExcel.write(os, ImportRequest.class).sheet("测试1").doWrite(dataList1);
            EasyExcel.write(os2, ImportRequest.class).sheet("测试2").doWrite(dataList2);


            // 注意这里必须要flush，否则无法读取到内容
            os.flush();
            os2.flush();
            byte[] byteArray = baos.toByteArray();
            System.out.println(byteArray.length);


            File file = new File("C:\\Users\\44674\\Desktop\\test\\导出5.xls");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(byteArray);
        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
        }

//        try (ByteArrayOutputStream baos2 = new ByteArrayOutputStream()) {
//            EasyExcel.write(baos, ImportRequest.class)
//                    .sheet("Sheet1") // 创建第一个Sheet
//                    .doWrite(dataList1); // 写入第一个Sheet的数据
//
//            // 开始追加写第二个Sheet
//            EasyExcel.write(baos, ImportRequest.class, true) // 追加模式
//                    .sheet("Sheet2") // 创建第二个Sheet
//                    .doWrite(dataList2); // 写入第二个Sheet的数据
//
//            // 注意这里必须要flush，否则无法读取到内容
//            baos.flush();
//            byte[] byteArray = baos.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}


