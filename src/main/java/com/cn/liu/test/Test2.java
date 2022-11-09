package com.cn.liu.test;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author lxw
 * @description 描述
 * @date 2022/11/7 15:37
 */
public class Test2 {

    /**
     * 生成普通密码
     * @param password 明文密码
     * @return String md5加密密码
     */
    public static String getMD5(String password) {
        password = md5Hex(password);
        System.out.println("password:" + password);
        return password;
    }

    /**
     * 生成含有随机盐的密码
     * @param password 要加密的密码
     * @return String 含有随机盐的密码
     */
    public static String getSaltMD5(String password) {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String salt = sBuilder.toString();
        System.out.println("salt:" + salt); // 3310094535652904

        String md5pwd = md5Hex(password + salt);
        System.out.println("md5pwd:" + md5pwd); // 207b7038757180b5c6c4e95247075923

        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            /**存md5值*/
            cs[i] = md5pwd.charAt(i / 3 * 2);
            // md5pwd.charAt(i / 3 * 2) : 0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30
            // cs[i] : 0,3,6,9,12,15,18,21,24,27,30,33,36,39,42,45

            /**存盐值*/
            cs[i + 1] = salt.charAt(i / 3);
            // salt.charAt(i / 3) : 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
            // cs[i + 1] : 1,4,7,10,13,16,19,22,25,28,31,34,37,40,43,46

            /**存md5值*/
            cs[i + 2] = md5pwd.charAt(i / 3 * 2 + 1);
            // md5pwd.charAt(i / 3 * 2 + 1) : 1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31
            // cs[i + 2] : 2,5,8,11,14,17,20,23,26,29,32,35,38,41,44,47
        }
//        char[] cs2 = {
//                '密','1','密',
//                '密','4','密',
//                '密','7', '密',
//                '密','10','密',
//                '密','13','密',
//                '密','16','密',
//                '密','19','密',
//                '密','22','密',
//                '密','25','密',
//                '密','28','密',
//                '密','31','密',
//                '密','34','密',
//                '密','37','密',
//                '密','40','密',
//                '密','43','密',
//                '密','46','密'
//        }
        return String.valueOf(cs);
    }


    /**
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     * @param str 要加密的内容
     * @return String 返回加密后的十六进制字符串
     */
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return hex(digest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }

    /**
     * byte[]字节数组 转换成 十六进制字符串
     * @param arr 要转换的byte[]字节数组
     * @return String 返回十六进制字符串
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }



    public static void main(String[] args) {
//        getSaltMD5("123456");
//        System.out.println(47 / 3);

        getMD5("123456");

//        String s = "";
//        for (int i = 0; i < 48; i += 3) {
//            s = s + (i + 1) + ",";
//        }
//        System.out.println(s);
        // 0,3,6,9,12,15,18,21,24,27,30,33,36,39,42,45

        // 1,4,7,10,13,16,19,22,25,28,31,34,37,40,43,46

        // 2,5,8,11,14,17,20,23,26,29,32,35,38,41,44,47
    }
}
