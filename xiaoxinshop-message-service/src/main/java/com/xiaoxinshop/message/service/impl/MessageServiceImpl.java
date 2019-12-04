package com.xiaoxinshop.message.service.impl;

import com.xiaoxinshop.message.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * @Author 小浩
 * @Date 2019/12/4 17:14
 * @Version 1.0
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public boolean sendSms(String phone, String code) {
        return SmsUtil.sendSms(phone, code);
    }
}
