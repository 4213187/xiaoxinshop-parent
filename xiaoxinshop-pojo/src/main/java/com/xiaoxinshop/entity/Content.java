package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content implements Serializable {
    private Long id;

    private Long categoryId;

    private String title;

    private String url;

    private String pic;

    private String status;

    private Integer sortOrder;


}