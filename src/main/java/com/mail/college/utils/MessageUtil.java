package com.mail.college.utils;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Value;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lemon
 */
public class MessageUtil {

    @Value("${zhenzikj.appid}")
    private static String appId;

    @Value("${zhenzikj.appsecret}")
    private static String appSecret;

    public static String sendMsg(String phone){
        // todo 效验手机号是否合法
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", appId, appSecret);
        Map<String,Object> params = new HashMap<>(8);
        params.put("number", phone);
        params.put("templateId","781");
        String[] templateParams = new String[2];
        templateParams[0] = verifyCode;
        templateParams[1] = "3";
        params.put("templateParams",templateParams);
        try {
            client.send(params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return verifyCode;
        }
    }
}
