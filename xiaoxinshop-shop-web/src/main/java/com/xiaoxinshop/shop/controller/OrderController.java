package com.xiaoxinshop.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoxinshop.entity.Order;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.entity.Seller;
import com.xiaoxinshop.order.service.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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


    @CrossOrigin(origins = "http://localhost:63343" ,allowCredentials = "true")
    @RequestMapping("/findBySellerIdAndStatus")
    public PageResult findBySellerIdAndStatus(int  pageNum, int pageSize, String status, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Seller seller = (Seller) session.getAttribute("seller");
        return  orderService.findBySellerIdAndStatus(pageNum, pageSize, seller.getSellerId(), status);
    }


    @RequestMapping("/update")
    public ResultVo update(String orderId,String status){
        System.out.println("orderId:" +orderId+"status:"+status );
        Order order = new Order();
        order.setStatus(status);
        order.setOrderId(orderId);
        try {
            orderService.update(order);
            return  new ResultVo(true,"发货成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultVo(false,"发货失败");
        }

    }

}
