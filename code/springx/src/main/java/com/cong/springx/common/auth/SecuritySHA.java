package com.cong.springx.common.auth;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecuritySHA {

    public static String str = "qwertyu";
    public static void main(String[] args) throws UnsupportedEncodingException {
        test1();
        test2();
    }

    public static void test1() throws UnsupportedEncodingException {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha256.digest(str.getBytes("utf-8"));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void test2() throws UnsupportedEncodingException {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-512");
            byte[] digest = sha256.digest(str.getBytes("utf-8"));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
