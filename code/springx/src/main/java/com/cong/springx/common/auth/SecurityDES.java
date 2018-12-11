package com.cong.springx.common.auth;

import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * https://blog.csdn.net/u011781521/article/details/77932321
 */
public class SecurityDES extends Coder {

    public static final String ALGORITHM =  "DES";

    /**
     * 解密
     */
     public static byte[] decrypt(byte[] data, String key) throws Exception {
         //对秘钥处理获得Key对象
         SecretKey secretKey = getSecretKey(key);

         Cipher cipher = Cipher.getInstance(ALGORITHM);
         cipher.init(Cipher.DECRYPT_MODE,secretKey);
         return cipher.doFinal(data);
     }

    private static SecretKey getSecretKey(String key) throws Exception {
        /*DESKeySpec desKeySpec = new DESKeySpec(decryptBASE64(key));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(desKeySpec);*/
        SecretKeySpec secretKeySpec = new SecretKeySpec(decryptBASE64(key),ALGORITHM);
        return secretKeySpec;

    }

    /**
     * 加密
     */
    public static byte[]  encrypt(String data,String key) throws Exception {
        byte[] bytes = data.getBytes("utf-8");
        SecretKey secretKey = getSecretKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(bytes);
    }

    /**
     * 生成秘钥
     * @param
     */
     public static String initKey() throws Exception {
         return initKey(null);
     }

     public static String initKey(String seed) throws Exception {

         SecureRandom secureRandom = null;
         if ( !StringUtils.isEmpty(seed)) {
              secureRandom = new SecureRandom(decryptBASE64(seed));
         } else {
             secureRandom =  new SecureRandom();
         }

         KeyGenerator keyInstance = KeyGenerator.getInstance(ALGORITHM);
         keyInstance.init(secureRandom);
         SecretKey secretKey = keyInstance.generateKey();
         return encryptBASE64(secretKey.getEncoded());
    }

    public static void main(String[] args) throws Exception {
        String input = "aaa";
        String  key = SecurityDES.initKey();
        System.out.println("原文:" + input);
        System.out.println("秘钥:" + key);

        byte[] encrypt = SecurityDES.encrypt(input, key);
        System.out.println("加密后 :" + SecurityDES.encryptBASE64(encrypt)); //

        byte[] decrypt = SecurityDES.decrypt(encrypt, key);
        System.out.println("解密后 :" + new String(decrypt,"utf-8"));
    }
}
