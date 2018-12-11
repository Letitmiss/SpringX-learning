package com.cong.springx.common.auth;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * https://blog.csdn.net/u011781521/article/details/77932321
 */
public class SecurityAES extends Coder {

    public static final String ALGORITHM =  "AES";

    public static final  String DEFAULT_SEED = "abcd1234@#$";

    /**
     * 加密
     */
    public static final String encryptAES(String data,String key) throws Exception {
        Key secretKey = initKey(128, key);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);

        byte[] bytes = cipher.doFinal(data.getBytes("utf-8"));
        return encryptBASE64(bytes);
    }


    /**
     * 解密
     */
    public static String decryptAES(String data,String key) throws Exception {
        Key secretKey = initKey(128, key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,secretKey);

        byte[] decrypt = cipher.doFinal(decryptBASE64(data));
        return new String(decrypt,"utf-8");

    }

    /**
     * 初始化key
     */
    public static Key initKey(int length,String seed) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(length,getSecureRandom(seed));
        return keyGenerator.generateKey();
    }

    public static SecureRandom getSecureRandom(String seed) throws Exception {
        if (seed == null || seed.trim().length() == 0) {
            seed = DEFAULT_SEED;
        }
        //SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        SecureRandom instance = SecureRandom.getInstance("SHA1PRNG", "SUN");
        instance.setSeed(seed.getBytes("utf-8"));
        return instance;
    }

    /**
     * 生成秘钥
     */
     public static String  getKey(String seed,int length) throws Exception {
         return encryptBASE64(initKey(length,seed).getEncoded());
     }


    public static void main(String[] args) throws Exception {

        String data = "gaocong";
        String key = getKey("123", 128);
        System.out.println("key :" + key);

        String encryptAES = SecurityAES.encryptAES(data, key);
        System.out.println("加密 :" + encryptAES);

        String decryptAES = SecurityAES.decryptAES(encryptAES, key);
        System.out.println("解密:" + decryptAES);
    }
}
