package com.myweb.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class Md5 {

    public final static  String md5key = "lagou";
    /**
     * MD5方法
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text+key);//加密
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    /**
     * MD5验证方法
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);//验证
        if(md5Text.equalsIgnoreCase(md5))//equalsIgnoreCase()是Java中String类的一个方法，用于比较两个字符串的内容是否相同，同时忽略大小写差异
        {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }


}
