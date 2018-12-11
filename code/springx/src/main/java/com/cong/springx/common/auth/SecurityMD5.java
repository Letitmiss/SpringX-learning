package com.cong.springx.common.auth;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *  https://blog.csdn.net/u011781521/article/details/77925608 消息摘要算法
 *   16进制 32位
 */
public class SecurityMD5 {

    public static String str = "1234567";
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        test1();
    }

    public static void test1 () throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(str.getBytes("utf-8"));
        String encode = Base64.getEncoder().encodeToString(digest);
        System.out.println(encode);
        String s = new BigInteger(1, digest).toString(16);
        System.out.println(s);

    }
}
