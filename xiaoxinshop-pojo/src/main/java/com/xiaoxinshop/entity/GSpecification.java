package com.xiaoxinshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 小浩
 * @Date 2019/11/15 15:27
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GSpecification implements Serializable {

   private  Specification specification;
   private  List<SpecificationOption>  specificationOptionList;


}
