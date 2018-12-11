package com.cong.springx.common.auth;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class SecurityRSA extends Coder {

    public static final String ALGORITHM = "RSA";

    public static final String SINGATURE_ALGORITHM = "MD5withRSA";

    /**
     * 用私钥 生成数字签名
     */
    public static String sign(byte[] data,String privateKey) throws Exception {
        byte[] decryptBASE64 = decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decryptBASE64);

        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privatekey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance(SINGATURE_ALGORITHM);
        signature.initSign(privatekey);
        signature.update(data);
        return  encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     */
    public static boolean verify(byte[] data,String publickey, String sign) throws Exception {
        byte[] keyBytes = decryptBASE64(publickey);

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance(SINGATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    /**
     * 加密 : 公钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptRSAPublic(byte[] data, String key) throws Exception {

        byte[] keyBytes = decryptBASE64(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 加密 私钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptRSAPrivate(byte[] data, String key) throws Exception {
        byte[] bytes = decryptBASE64(key);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        return cipher.doFinal(data);

    }


    /**
     * 解密 : 使用公钥
     * @return
     * @throws Exception
     */
     public static byte[] decryptRSAPublic(byte[] data, String key) throws Exception {
         byte[] keyBytes = decryptBASE64(key);

         X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
         PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

         Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
         cipher.init(Cipher.DECRYPT_MODE,publicKey);
         return cipher.doFinal(data);
     }


    /**
     * 解密 : 私钥
     * @return
     * @throws Exception
     */
     public static byte[] decryptRSAPrivate(byte[] data,String key) throws Exception {
         byte[] bytes = decryptBASE64(key);

         PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
         KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
         PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

         Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
         cipher.init(Cipher.DECRYPT_MODE,privateKey);
         return  cipher.doFinal(data);
     }



    public static Map<String,String> getKey() throws  Exception{

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("RSAPublicKey",encryptBASE64(publicKey.getEncoded()));
        keyMap.put("RSAPrivateKey",encryptBASE64(privateKey.getEncoded()));

        return keyMap;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> key = SecurityRSA.getKey();
        String rsaPublicKey = key.get("RSAPublicKey");
        String rsaPrivateKey = key.get("RSAPrivateKey");
        System.out.println("public :" + rsaPublicKey);
        System.out.println("private:" + rsaPrivateKey);
        String input = "abcdefg";
        byte[] data = input.getBytes("utf-8");
        System.out.println("公钥加密--- 私钥解密");

        byte[] encryptRSAPublic = SecurityRSA.encryptRSAPublic(data, rsaPublicKey);
        byte[] bytes = SecurityRSA.decryptRSAPrivate(encryptRSAPublic, rsaPrivateKey);
        System.out.println(new String(bytes,"utf-8"));

        System.out.println("私钥加密 --- 公钥解密");

        byte[] bytes1 = SecurityRSA.encryptRSAPrivate(data, rsaPrivateKey);
        byte[] bytes2 = SecurityRSA.decryptRSAPublic(bytes1, rsaPublicKey);
        System.out.println(new String(bytes2,"utf-8"));

    }
}
