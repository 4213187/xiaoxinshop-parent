package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDesc implements Serializable {
    private Long goodsId;

    private String introduction;

    private String specificationItems;

    private String customAttributeItems;

    private String itemImages;

    private String packageList;

    private String saleService;


}