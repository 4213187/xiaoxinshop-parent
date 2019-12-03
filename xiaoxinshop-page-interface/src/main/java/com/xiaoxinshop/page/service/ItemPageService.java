package com.xiaoxinshop.page.service;

/**
 * @Author 小浩
 * @Date 2019/12/2 18:15
 * @Version 1.0
 **/
public interface ItemPageService {
    /**
     * 生成静态页面
     * @param goodsId
     * @return
     */
    public boolean genItemHtml(Long goodsId);


    /**
     * 删除商品详细页
     * @return
     */
    public boolean deleteItemHtml(Long[] goodsIds);

}
