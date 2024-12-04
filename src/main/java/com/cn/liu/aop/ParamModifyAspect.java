package com.cn.liu.aop;

import com.cn.liu.annotation.ParamModify;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

@Aspect
@Component
public class ParamModifyAspect {

    @Pointcut("@annotation(com.cn.liu.annotation.ParamModify)")
    public void paramResetMethod() {}

    /*@Before("paramResetMethod()")
    public void resetParams(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        if (method.isAnnotationPresent(ParamModify.class)) {
            if (Objects.isNull(args) || args.length == 0) {
                return;
            }
            Object arg = args[0];
            Class<?> clazz = arg.getClass();

            Map<Class<?>, List<Field>> clazzFeildMap = new HashMap<>();
            while (clazz != null) {
                Field[] declaredFields = clazz.getDeclaredFields();
                clazzFeildMap.put(clazz, Arrays.asList(declaredFields));
                clazz = clazz.getSuperclass();
            }

            if (MapUtils.isNotEmpty(clazzFeildMap)) {
                for (Map.Entry<Class<?>, List<Field>> entry : clazzFeildMap.entrySet()) {
                    Class<?> keyClass = entry.getKey();
                    List<Field> fields = entry.getValue();

                    if (fields.stream().anyMatch(item -> "createUser".equals(item.getName()))) {
                        Field field = keyClass.getDeclaredField("createUser");
                        resetField(arg, field);
                    }
                    if (fields.stream().anyMatch(item -> "createDept".equals(item.getName()))) {
                        Field field = keyClass.getDeclaredField("createDept");
                        resetField(arg, field);
                    }
                    if (fields.stream().anyMatch(item -> "updateUser".equals(item.getName()))) {
                        Field field = keyClass.getDeclaredField("updateUser");
                        resetField(arg, field);
                    }
                }
            }
        }
    }*/

    @Before("@annotation(paramModify)")
    public void resetParams(JoinPoint joinPoint, ParamModify paramModify) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        if (method.isAnnotationPresent(ParamModify.class)) {

            String[] fieldNames = paramModify.fields();
            String[] fieldvalues = paramModify.values();

            if (Objects.isNull(args) || args.length == 0) {
                return;
            }
            Object arg = args[0];
            Class<?> clazz = arg.getClass();

            Map<Class<?>, List<Field>> clazzFeildMap = new HashMap<>();
            while (clazz != null) {
                Field[] declaredFields = clazz.getDeclaredFields();
                clazzFeildMap.put(clazz, Arrays.asList(declaredFields));
                clazz = clazz.getSuperclass();
            }

            if (MapUtils.isNotEmpty(clazzFeildMap)) {
                for (Map.Entry<Class<?>, List<Field>> entry : clazzFeildMap.entrySet()) {
                    Class<?> keyClass = entry.getKey();
                    List<Field> fields = entry.getValue();

                    if (fieldNames.length > 0) {
                        for (int i = 0; i < fieldNames.length; i++) {
                            String fieldName = fieldNames[i];
                            String fieldValue = fieldvalues[i];

                            if (fields.stream().anyMatch(item -> fieldName.equals(item.getName()))) {
                                Field field = keyClass.getDeclaredField(fieldName);
                                boolean accessible = ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible());
                                if (accessible) {
                                    field.setAccessible(true);
                                    field.set(arg, StringUtils.isEmpty(fieldValue) ? null : Long.valueOf(fieldValue));
                                }
                            }
                        }
                    }

                    /*if (fields.stream().anyMatch(item -> "createUser".equals(item.getName()))) {
                        Field field = keyClass.getDeclaredField("createUser");
                        resetField(arg, field);
                    }
                    if (fields.stream().anyMatch(item -> "createDept".equals(item.getName()))) {
                        Field field = keyClass.getDeclaredField("createDept");
                        resetField(arg, field);
                    }
                    if (fields.stream().anyMatch(item -> "updateUser".equals(item.getName()))) {
                        Field field = keyClass.getDeclaredField("updateUser");
                        resetField(arg, field);
                    }*/
                }
            }
        }
    }

    private void resetField(Object arg, Field field) throws IllegalAccessException {
        boolean accessible = ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible());
        if (accessible) {
            field.setAccessible(true);
            field.set(arg, null);
        }
    }

}
