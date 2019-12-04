package com.xiaoxinshop.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xiaoxinshop.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.util.List;
import java.util.Map;

/**
 * @Author 小浩
 * @Date 2019/12/4 17:21
 * @Version 1.0
 **/
public class SmsListener implements MessageListener {
    @Autowired
    private MessageService messageService;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        String text = null;
        try {
            text = textMessage.getText();
            Map<String,String> map = (Map<String,String>)JSONArray.parse(text);

            String phone = map.get("phone");
            String code = map.get("code");
            System.out.println(phone +" "+ code);
            System.out.println(messageService.sendSms(phone,code));
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
