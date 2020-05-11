package com.xiaoxinshop.message.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Author 小浩
 * @Date 2019/12/4 17:29
 * @Version 1.0
 **/
public class NoticeSmsUtil {
    public static boolean sendSms(String phone,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIHhbkCKakCoOs", "PzLy4vwk3ARcJV1g807m10YhmQJD05");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "叉叉打卡");
        request.putQueryParameter("TemplateCode", "SMS_189763113");
        request.putQueryParameter("TemplateParam", "{\"name\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            if (response.getData().indexOf("OK")>=0){
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

        return true;
    }
}
