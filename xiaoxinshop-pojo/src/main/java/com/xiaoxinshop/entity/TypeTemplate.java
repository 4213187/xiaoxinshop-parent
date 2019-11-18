package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TypeTemplate implements Serializable {
    private Long id;

    private String name;

    private String specIds;

    private String brandIds;

    private String customAttributeItems;


}