# SpringX-learning
learning...
----
# SprinngBoot
### [1.SpringBoot简介](https://github.com/Letitmiss/SpringX-learning/blob/master/blog/01.springboot1.md)
### [2.SpringBoot项目配置](https://github.com/Letitmiss/SpringX-learning/blob/master/blog/01.springboot2.md)
### [3.Controller的配置](https://github.com/Letitmiss/SpringX-learning/blob/master/blog/01.springboot3.md)

使用@Retryable和@Recover实现重处理 


https://docs.servicecomb.io/java-chassis/zh_CN/edge/by-servicecomb-sdk.html



ak：T7TD91OULQFEFSB7RU6I sk ： 792fdRxayIiMwdkDWCJI979PiWKYn4MOq5ssQ06M 


# SprinngCloud 

HAHA

1. 加入eureka的客户端,配置cloud , eureka-client
2. 加入@enableEurekaClient
3. 配置服务的名称和 eureka.server-url.defaultZone=http://localhost:8671/eureka
4. ribbon引入 , 在 RestTemplate 方法 @LoadBalanced, 调用的时候: uri:http://{服务名}/{uri}
5. 启动访问的

注册中心高可用集群   
互相注册    
1. eureka1 注册中心   
eureka.client.server-url.dofaultZone=http://{eureka2}:8672/eureka   

2. 提供者需要两个注册地址    
用,号隔开   

自我保护    
服务端配置:    
eureka.server.enable-self-preservation=false #禁用自我保护机制    
客户端配置:    
隔几秒   
eureka.instance.lease-renewal-interval-in-seconds=2   
多少秒之后没故障,就提出我这个服务   
eureka.instance.lease-expiration-duration-in-seconds=10

# ribbon

1. 创建多服务提供之者   
* 复制项目 :  重构命名 , 修改端口(同一个机器的端口)
IRule 负载均衡规则;
```
@Bean
public IRule() {
rerurn new RandomRule(); //随机策略
}
```

## 配置中心
package com.cong.auth.study;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class Coder {

    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";


    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        return sha.digest(data);

    }

}


package com.cong.auth.study;

import sun.security.util.Length;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.sound.midi.Soundbank;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
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


package com.cong.auth.study;



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


package com.cong.auth.study;

import sun.security.x509.EDIPartyName;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public class SecurityCert extends Coder {

    public static final String KEY_STORE = "JKS";

    /**
     * 对数据加密 : 私钥
     */
     public static byte[] encryptByPrivate(byte[] data, String keyStroePath,String alias, String password) throws Exception {
         PrivateKey privatKey = getPrivatKey(keyStroePath,alias,password);
         Cipher cipher = Cipher.getInstance(privatKey.getAlgorithm());
         cipher.init(Cipher.ENCRYPT_MODE,privatKey);
         return cipher.doFinal(data);
     }

    /**
     * 对数据解密 : 私钥
     */
    public static byte[] decryptByPrivate(byte[] data, String keyStroePath,String alias, String password) throws Exception {
        PrivateKey privatKey = getPrivatKey(keyStroePath,alias,password);
        Cipher cipher = Cipher.getInstance(privatKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,privatKey);
        return cipher.doFinal(data);
    }


    /**
     * 对数据加密 : 公钥
     */
    public static byte[] encryptByPublic(byte[] data, String certificatePath) throws Exception {
        PublicKey publicKey = getPublicKey(certificatePath);
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 对数据解密 : 公钥
     */
    public static byte[] decryptByPublic(byte[] data, String certificatePath) throws Exception {
        PublicKey publicKey = getPublicKey(certificatePath);
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 验证证书
     */
    public static boolean verifyCertificate(String certificatePath) {
        return verifyCertificate(new Date(),certificatePath);
    }

    /**
     * 验证Certificate是否过期或无效
     *
     * @param date
     * @param certificatePath
     * @return
     */
    public static boolean verifyCertificate(Date date, String certificatePath) {
        boolean status = true;
        try {
            // 取得证书
            Certificate certificate = getCertificate(certificatePath);
            // 验证证书是否过期或无效
            status = verifyCertificate(date, certificate);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    /**
     * 验证证书是否过期或无效
     *
     * @param date
     * @param certificate
     * @return
     */
    private static boolean verifyCertificate(Date date, Certificate certificate) {
        boolean status = true;
        try {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            x509Certificate.checkValidity(date);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    /**
     * 签名
     *
     * @param keyStorePath
     * @param alias
     * @param password
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] sign, String keyStorePath, String alias,String password) throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate(keyStorePath, alias, password);
        // 获取私钥
        KeyStore ks = getKeyStore(keyStorePath, password);
        // 取得私钥
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());

        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(sign);
        return encryptBASE64(signature.sign());
    }

    /**
     * 验证签名
     * @param data
     * @param sign
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String sign,String certificatePath) throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
        // 获得公钥
        PublicKey publicKey = x509Certificate.getPublicKey();
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(data);

        return signature.verify(decryptBASE64(sign));

    }

    /**
     * 验证 key  Certificate
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     */
    public static boolean verifyCertificate(Date date, String keyStorePath, String alias, String password) {
        boolean status = true;
        try {
            Certificate certificate = getCertificate(keyStorePath, alias,password);
            status = verifyCertificate(date, certificate);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    /**
     * 验证 Certificate
     *
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     */
    public static boolean verifyCertificate(String keyStorePath, String alias,String password) {
        return verifyCertificate(new Date(), keyStorePath, alias, password);
    }

    /**
     * 获得keyStore
     */
     private static KeyStore getKeyStore(String keystorePath,String passwd) throws Exception {
         FileInputStream inputStream = new FileInputStream(keystorePath);
         KeyStore keyStore = KeyStore.getInstance(KEY_STORE);
         keyStore.load(inputStream,passwd.toCharArray());
         inputStream.close();
         return keyStore;
     }

     private static PrivateKey getPrivatKey(String keystorePath, String alias,String password) throws Exception {
         KeyStore keyStore = getKeyStore(keystorePath, password);
         PrivateKey key = (PrivateKey) keyStore.getKey(alias, password.toCharArray());

         return key;
     }

     private static PublicKey getPublicKey(String certificatePath) throws Exception {
       Certificate certificate = getCertificate(certificatePath);
         PublicKey publicKey = certificate.getPublicKey();
         return publicKey;
     }

    private static Certificate getCertificate(String certificatePath) throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        FileInputStream inputStream = new FileInputStream(certificatePath);
        Certificate certificate = certificateFactory.generateCertificate(inputStream);
        inputStream.close();
        return certificate;
    }
    /**
     * 获得Certificate
     *
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    private static Certificate getCertificate(String keyStorePath,String alias, String password) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, password);
        Certificate certificate = ks.getCertificate(alias);
        return certificate;
    }

    public static void main(String[] args) throws Exception {
        String password = "123456";
        String alias = "www.cong.com";
        String certificatePath = "d:\\cong.cer";
        String keystorePath = "d:\\cong.keystore";

        System.out.println("公钥加密 == 私钥解密");
        String input = "gege";
        byte[] data = input.getBytes("utf-8");

        byte[] encryptByPublic = SecurityCert.encryptByPublic(data, certificatePath);

        byte[] decryptByPrivate = SecurityCert.decryptByPrivate(encryptByPublic, keystorePath, alias, password);

        System.out.println(new String(decryptByPrivate,"utf-8"));

        //验证证书
        SecurityCert.verifyCertificate(keystorePath);

        byte[] encryptByPrivate = SecurityCert.encryptByPrivate(data, keystorePath, alias, password);
        byte[] decryptByPublic = SecurityCert.decryptByPublic(encryptByPrivate, certificatePath);
        System.out.println(new String(decryptByPublic,"utf-8"));

        //产生签名:
        String sign = SecurityCert.sign(encryptByPrivate, keystorePath, alias, password);
        System.out.println( "签名"+sign);

        boolean verify = SecurityCert.verify(encryptByPrivate, sign, certificatePath);
        System.out.println(verify);

    }

}


package com.cong.auth.study;

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


package com.cong.auth.study;

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

package com.cong.auth.study;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECField;
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


package com.cong.auth.study;

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



package com.cong.auth.utils;

import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.sql.rowset.CachedRowSet;
import java.awt.image.RescaleOp;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;

/**
 * 证书密码是123456
 * alias : www.cong.auth.com
 */
public class SecurityUtils {

    public static final String KEY_STORE = "JKS";

    public static final String  CLASSES_PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static final String ALGORITHM_RSA = "RSA";

    public static final String KEYSTORE_PATH = CLASSES_PATH+"/certificate/auth.keystore";
    public static final String CERTIFICATE_PATH = CLASSES_PATH+"/certificate/auth.cer";
    public static final String ALIAS = "www.cong.auth.com";
    public static final String PASSWD = "123456";


    /**
     * 私钥加密
     * @param data
     * @param keystorePath
     * @return
     */
    public static byte[] encryptRSAByPrivate(byte[] data, String keystorePath, String alias, String passwd) throws Exception {
        //获取私钥
        PrivateKey privateKey = getPrivateKey(keystorePath, alias, passwd);
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        return cipher.doFinal(data);
    }
    /**
     * @param keystorePath
     * @param alias
     * @param passwd
     * @return
     */
    private static PrivateKey getPrivateKey(String keystorePath, String alias, String passwd) throws Exception {
        FileInputStream inputStream = new FileInputStream(keystorePath);
        KeyStore keyStore = KeyStore.getInstance(KEY_STORE);
        keyStore.load(inputStream, passwd.toCharArray());
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, passwd.toCharArray());
        return privateKey;
    }

    /**
     * 私钥加密
     * @param data
     * @return
     */
    public static String encryptRSAByPrivate(byte[] data) throws Exception {
        //获取私钥
        byte[] encryptRSAByPrivate = encryptRSAByPrivate(data, KEYSTORE_PATH, ALIAS, PASSWD);
        return encryptBASE64(encryptRSAByPrivate);
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

}

package com.cong.vod.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterManager {

    private static final String[] FILTER_PATTERNS = {"/v1.0/*", "/v1.1/*"};

    @Bean
    public FilterRegistrationBean CustomFilter() {
        FilterRegistrationBean customFilter = new FilterRegistrationBean();
        customFilter.setFilter(authFilter());
        customFilter.addUrlPatterns(FILTER_PATTERNS);
        customFilter.setName("authFilter");
        customFilter.setOrder(1);
        return  customFilter;
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}


package com.cong.vod.filter;

import com.cong.vod.auth.Token;
import com.cong.vod.auth.TokenService;
import com.cong.vod.controller.SystemConfig;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final String AUTH_TOKEN = "X-Auth-Token";
    private static final String AUTH_TOKEN_DECODE = "X-Auth-Token-Decode";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        try {
            if (SystemConfig.TOKEN_SWITCH) {

                String tokenStr = request.getHeader(AUTH_TOKEN);
                Token token = TokenService.decryptToken(tokenStr);

                String requestURI = request.getRequestURI();

                TokenService.validateToken(token,tokenStr,request.getMethod(),requestURI);
                //设置到请求中
                request.setAttribute(AUTH_TOKEN_DECODE,token);

                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                filterChain.doFilter(servletRequest,servletResponse);
            }

        } catch (Exception e) {
            //日志
            //返回
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            HttpServletUtil.writeStringToRsp(response,new SingleReturn().toString());
        } finally {
                //记录日志
        }
    }

    @Override
    public void destroy() {

    }
}


package com.cong.vod.common.exception;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@Configuration
@Component
@ControllerAdvice //统一异常处理
public class GlobalException {

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    /**
     * 拦截自定义异常并且作为返回值处理,同时可以设置http响应码
     * @param ex
     * @param response
     * @return
     */
    @ExceptionHandler(VodException.class)
    @ResponseBody
    public ErrorRsp handleVodExceptionHandle(VodException ex, HttpServletResponse response) {

        ErrorRsp errorRsp = new ErrorRsp(ex.getCode(),ex.getMsg());
        if (ex.getStatus() != 0) {
            response.setStatus(ex.getStatus());
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
        return errorRsp;
    }

    /**
     * 拦截: 空指针异常,抛出自定义异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorRsp handleNullPointerException(NullPointerException ex)
    {
        ErrorRsp errorRsp = new ErrorRsp();
        INFO_LOG.error("ErrorCode:500, ErrorMessage:Internal System Error, NullPointerException occur.\n",ex);
        errorRsp.setErrorCode(ErrorCode.VOD);
        errorRsp.setErrorMsg("Internal System Error, NullPointerException");
        return errorRsp;
    }
}







