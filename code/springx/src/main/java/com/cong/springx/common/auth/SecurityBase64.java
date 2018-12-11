package com.cong.springx.common.auth;



import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *  Base64 :Base64就是用来将非ASCII字符的数据转换成ASCII字符的一种方法，而且base64特别适合在http，mime协议下快速传输数据。
 *  MD5
 *  SHA
 *  HMAC
 *  97979797

    01100001 01100001 01100001 01100001

    011000 010110 000101 100001 011000  01  0000

    24 22 5 33 24  补4个0 转化==
     y  W F  h  Y    ==
     YWFhYQ==
 *
 */
public class SecurityBase64 {
     //https://my.oschina.net/benhaile/blog/267738
     //https://blog.csdn.net/weixx3/article/details/81266506
     //https://blog.csdn.net/tomatocc/article/details/77881421
    public static void main(String[] args) throws UnsupportedEncodingException {
        //基础
        byte[] bytes = "aaaa".getBytes("utf-8");
        String encode = Base64.getEncoder().encodeToString("aaaa".getBytes("utf-8"));
        System.out.println(encode);

        byte[] decode = Base64.getDecoder().decode(encode.getBytes("utf-8"));
        String s = new String(decode, "utf-8");
        System.out.println(s);

        //URL编码
        String encode1 = Base64.getUrlEncoder().encodeToString("11/aaa?bb=cc".getBytes("utf-8"));
        System.out.println("encode: " + encode1 );
        byte[] decode1 = Base64.getDecoder().decode(encode1.getBytes("utf-8"));
        System.out.println(new String(decode1,"utf-8"));
    }
}
