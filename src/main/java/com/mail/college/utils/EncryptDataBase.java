package com.mail.college.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

public class EncryptDataBase {

    /**
     * 加密方法
     * @param plainText 需加密文本
     */
    private static void encrypt(String plainText) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        // 加密的算法，这个算法是默认的
        config.setAlgorithm("PBEWithMD5AndDES");
        //加密的密钥，自定义
        config.setPassword("password");
        standardPBEStringEncryptor.setConfig(config);
        System.out.println(standardPBEStringEncryptor.encrypt(plainText));
    }

    /**
     * 解密方法
     * @param encryptedText 需解密文本
     */
    private static void decrypt(String encryptedText) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        // 解密的算法，需同加密算法相同
        config.setAlgorithm("PBEWithMD5AndDES");
        //解密的密钥，需同加密密钥相同
        config.setPassword("password");
        standardPBEStringEncryptor.setConfig(config);
        System.out.println(standardPBEStringEncryptor.decrypt(encryptedText));
    }

    public static void main(String[] args) {
        encrypt("root");
        decrypt("51NlHUTeB0eyFzIpyQ8Pe9VEkGbDwQqS");
    }
}
