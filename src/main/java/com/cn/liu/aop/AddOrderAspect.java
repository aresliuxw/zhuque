package com.cn.liu.aop;

import com.alibaba.fastjson.JSON;
import com.cn.liu.annotation.AddOrder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

//@Aspect
//@Component
public class AddOrderAspect {

    @Pointcut("@annotation(com.cn.liu.annotation.AddOrder)")
    public void addOrderMethod() {}

    @Before("addOrderMethod()")
    public void validateParams(JoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取目标方法
        Method method = signature.getMethod();

        // 获取方法参数值
//        Object[] args = joinPoint.getArgs();
        // 获取方法参数类型
//        Class<?>[] parameterTypes = method.getParameterTypes();
        // 获取方法参数上的注解
//        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//
//        for (int i = 0; i < parameterAnnotations.length; i++) {
//            for (Annotation annotation : parameterAnnotations[i]) {
//                if (annotation.annotationType().equals(AddOrder.class)) {
//                    // 这里可以对参数进行验证或其他操作
//                    System.out.println("Validating parameter: " + args[i]);
//                    // 示例：检查参数是否为空
//                    if (args[i] == null) {
//                        throw new IllegalArgumentException("Parameter cannot be null");
//                    }
//                    // 你可以根据需要添加更多验证逻辑
//                }
//            }
//        }

//        if (method.isAnnotationPresent(AddOrder.class)) {
//            AddOrder annotation = method.getAnnotation(AddOrder.class);
////            System.out.println("Found @ValidateParam on method: " + method.getName());
////            System.out.println("Annotation value: " + annotation.value());
//
//            for (Object arg : args) {
//                System.out.println("=====:" + arg.toString());
//            }
//        }
//
//        // 打印所有参数作为示例
//        System.out.println("Method arguments are: " + Arrays.toString(args));


        if (method.isAnnotationPresent(AddOrder.class)) {
            // 获取当前的HTTP请求
            HttpServletRequest originalRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // 包装原始请求
            ModifiableHttpServletRequest modifiableRequest = new ModifiableHttpServletRequest(originalRequest);

            // 修改请求体内容
            String originalBody = modifiableRequest.getBody();
//        Map<String, Object> requestBody = JsonUtil.parseJsonToMap(originalBody); // 假设你有一个工具方法来解析JSON字符串到Map

            Map<String, Object> requestBody = JSON.parseObject(originalBody, Map.class);

            // 修改指定的参数值
//        String param = modifyRequestBody.param();
//        String newValue = modifyRequestBody(newValue());
//        if (requestBody.containsKey(param)) {
//            requestBody.put(param, newValue);
//            modifiableRequest.setBody(JsonUtil.toJson(requestBody)); // 将修改后的Map转换回JSON字符串
//        }
            if (requestBody.containsKey("createUser")) {
                requestBody.put("createUser", null);
                modifiableRequest.setBody(JSON.toJSONString(requestBody)); // 将修改后的Map转换回JSON字符串
            }

            // 设置修改后的请求为当前请求
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() = modifiableRequest;
            request = modifiableRequest;
        }

    }

}
