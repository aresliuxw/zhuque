package com.cn.liu.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Enumeration;

//@Component
public class ParamInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方法类型
        String method = request.getMethod();

        // 获取GET请求参数
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (String paramValue : paramValues) {
                System.out.println("Parameter Name: " + paramName + ", Value: " + paramValue);
            }
        }

        // 获取POST请求体
        // 对于POST请求，如果需要获取请求体内容，可以这样做：
        if ("POST".equalsIgnoreCase(method)) {
            // 注意：此方法将消耗HttpServletRequest的输入流，因此请确保仅调用一次。
            String body = getBodyString(request);
            System.out.println("Request Body: " + body);
        }

        return true; // 继续处理链
    }

    private String getBodyString(HttpServletRequest request) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

}
