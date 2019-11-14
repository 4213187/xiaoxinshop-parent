package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements Serializable {
    private Long id;

    private String name;

    private String firstChar;


}