package com.cong.springx.common.auth;

import javax.crypto.Cipher;
import java.io.FileInputStream;
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
