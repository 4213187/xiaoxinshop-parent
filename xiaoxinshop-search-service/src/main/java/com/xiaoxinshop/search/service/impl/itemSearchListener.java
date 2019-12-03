package com.xiaoxinshop.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.xiaoxinshop.entity.Item;
import com.xiaoxinshop.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import sun.dc.pr.PRError;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Map;

/**
 * @Author 小浩
 * @Date 2019/12/3 21:10
 * @Version 1.0
 **/
public class itemSearchListener implements MessageListener {

    @Autowired
    private ItemSearchService itemSearchService;
    @Override
    public void onMessage(Message message) {
        System.out.println("监听到内容");
        TextMessage textMessage = (TextMessage)message;
        try {
            String text = textMessage.getText();
            List<Item> itemList = JSON.parseArray(text, Item.class);
            System.out.println(itemList);
            for(Item item:itemList){
                System.out.println(item.getId()+" "+item.getTitle());
                //将spec字段中的json字符串转换为map
                Map specMap= JSON.parseObject(item.getSpec());
                //给带注解的字段赋值
                item.setSpecMap(specMap);
            }

            itemSearchService.importList(itemList);
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
