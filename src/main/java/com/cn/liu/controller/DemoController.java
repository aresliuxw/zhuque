package com.cn.liu.controller;


import com.cn.liu.entity.PUser;
import com.cn.liu.entity.Result;
import com.cn.liu.service.DemoServiceInf;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * 测试
 *
 * @Author lxw
 * @Date 2022-04-05 17:54
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoServiceInf demoService;

    @GetMapping("/getUsers")
    public List<PUser> getUsers() {

        return demoService.getUsers();
    }

    @GetMapping("/exportUsers")
    public String exportUsers() {

        demoService.exportUsers();

        return "success";
    }

    /**
     * 根据链接下载文件 写入本地
     * @return
     */
    @GetMapping("/exportFile")
    public String exportFile() {
        String filePath = "https://q7.itc.cn/q_70/images03/20240430/cb1a63df65e740a790315fa556761cbe.jpeg";
        String outPath = "C:\\Users\\44674\\Desktop\\文件\\666.jpeg";
        try (FileInputStream inputStream = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(outPath);
        ) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            byte[] bytes = baos.toByteArray();

            fos.write(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "success";
    }

    /**
     * 浏览器下载文件
     */
    @GetMapping("/dowmloadFile")
    public Result<Object> dowmloadFile(HttpServletResponse response, @RequestParam String code) {
        if (StringUtils.isEmpty(code)) {
            return Result.builder().code("500").msg("code为空").build();
        }
        try (FileInputStream inputStream = new FileInputStream("C:\\Users\\44674\\Desktop\\测试.pdf");
             ServletOutputStream outputStream = response.getOutputStream();) {

            byte[] bytes = IOUtils.toByteArray(inputStream);

            String fileName = code + ".pdf";
            response.setContentType("application/octet-stream");
            response.setContentLength(bytes.length);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            return Result.builder().code("500").msg("系统异常").build();
        }
        return Result.builder().code("200").msg("成功").build();
    }

    public static void main(String[] args) {
        String fileUrl = "https://q7.itc.cn/q_70/images03/20240430/cb1a63df65e740a790315fa556761cbe.jpeg";
        String outputFilePath = "C:\\Users\\44674\\Desktop\\文件\\666.jpeg";

        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


