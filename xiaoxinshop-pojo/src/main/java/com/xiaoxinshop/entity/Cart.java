package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/12/9 16:58
 * @Version 1.0
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart implements Serializable {
    private  String sellerId;
    private  String sellerName;
    List<OrderItem> orderItemList ;

}
