package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/11/20 20:25
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GGoods implements Serializable {
    private  Goods goods;
    private  GoodsDesc goodsDesc;
    private List<Item> itemList;
}
