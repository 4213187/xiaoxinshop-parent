package com.xiaoxinshop.page.service.impl;

import com.xiaoxinshop.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @Author 小浩
 * @Date 2019/12/3 22:23
 * @Version 1.0
 **/
public class PageListener implements MessageListener {
    @Autowired
    private ItemPageService itemPageService;
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            Long id =(Long) objectMessage.getObject();
            System.out.println(id);
            itemPageService.genItemHtml(id);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
