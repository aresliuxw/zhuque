package com.cn.liu.test;

/**
 * @author lxw
 * @description 描述
 * @date 2022/11/7 15:37
 */
public class Test1 {

    /**
     * JVM默认Integer的常量池范围是[-128, 127]
     */
    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 10;
        System.out.println(a == b);// true

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);// false
    }
}
