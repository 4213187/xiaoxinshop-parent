package com.xiaoxinshop.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.xiaoxinshop.entity.*;
import com.xiaoxinshop.order.service.OrderService;
import com.xiaoxinshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 小浩
 * @Date 2019/12/20 21:23
 * @Version 1.0
 **/
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    OrderService orderService;

    @Reference
    SellerService sellerService;





    @CrossOrigin(origins = "http://localhost:63343" ,allowCredentials = "true")
    @RequestMapping("/findBySellerIdAndStatus")
    public PageResult findBySellerIdAndStatus(int  pageNum, int pageSize, String status, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Seller seller = (Seller) session.getAttribute("seller");
        return  orderService.findBySellerIdAndStatus(pageNum, pageSize, seller.getSellerId(), status);
    }


    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueNoticeDestination;


    @RequestMapping("/update")
    public ResultVo update(String orderId,String status){
        System.out.println("orderId:" +orderId+"status:"+status );
        Order order = new Order();
        order.setStatus(status);
        order.setOrderId(orderId);
        try {
            orderService.update(order);

            Order noticeOrder = orderService.findById(orderId);
            Seller seller = sellerService.findById(noticeOrder.getSellerId());
//          sms
            Map<String,String> map  = new HashMap<>();

            map.put("phone",noticeOrder.getReceiverMobile());
            map.put("code",seller.getNickName());
            final String jsonString = JSON.toJSONString(map);
            System.out.println(map);
            System.out.println("jmsTemplate");
            jmsTemplate.send(queueNoticeDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(jsonString);
                }
            });
            return  new ResultVo(true,"发货成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultVo(false,"发货失败");
        }

    }

}
