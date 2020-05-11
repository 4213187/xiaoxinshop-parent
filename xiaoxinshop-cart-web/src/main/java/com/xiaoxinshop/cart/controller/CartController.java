package com.xiaoxinshop.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.xiaoxinshop.cart.service.CartService;
import com.xiaoxinshop.entity.Cart;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/12/9 18:26
 * @Version 1.0
 **/

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {

    @Reference
    private CartService cartService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    @RequestMapping("/findCartList")
    public List<Cart> findCartList(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String cartListString = CookieUtil.getCookieValue(request, "cartList","UTF-8");
        if(cartListString==null || cartListString.equals("")){
            cartListString="[]";
        }
        List<Cart> cartList_cookie = JSON.parseArray(cartListString, Cart.class);
 //     未登录
        if ("anonymousUser".equals(username)){
            System.out.println("向cookies获取购物车");
            return cartList_cookie;
//      已登陆
        }else{

            List<Cart> cartListFromRedis = cartService.findCartListFromRedis(username);

            //如果本地存在购物车
            if(cartList_cookie.size()>0){
                //合并购物车
                cartListFromRedis=cartService.mergeCartList(cartListFromRedis, cartList_cookie);
                //清除本地cookie的数据
                CookieUtil.deleteCookie(request, response, "cartList");
                //将合并后的数据存入redis
                cartService.saveCartListToRedis(username, cartListFromRedis);
            }

            return  cartListFromRedis;

        }

    }


    @RequestMapping(value = "/addGoodsToCartList",method = RequestMethod.GET)
    public ResultVo addGoodsToCartList(Long itemId,Integer num){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Cart>  cartList= findCartList();
        cartList = cartService.addGoodsToCartList(cartList,itemId,num);
        try {
            if ("anonymousUser".equals(username)){
                System.out.println("向cookies存储购物车");
                String cookiesCartList = JSON.toJSONString(cartList);
                CookieUtil.setCookie(request,response,"cartList",cookiesCartList,3600*24,"UTF-8");
            }else {
               cartService.saveCartListToRedis(username,cartList);
            }

            return  new ResultVo(true,"添加购物车成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultVo(false,"添加购物车失败");
        }


    }


    @RequestMapping("/name")
    public String showName() {

        //得到登陆人账号
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(name);

        return name;
    }





}
