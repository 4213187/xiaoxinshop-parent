package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationOption implements Serializable {
    private Long id;

    private String optionName;

    private Long specId;

    private Integer orders;


}