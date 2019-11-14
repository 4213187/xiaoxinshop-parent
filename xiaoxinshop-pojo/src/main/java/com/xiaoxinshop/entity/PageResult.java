package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/11/13 16:42
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {

    private long total;

    private List rows;

}
