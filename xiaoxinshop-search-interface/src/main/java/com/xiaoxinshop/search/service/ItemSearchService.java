package com.xiaoxinshop.search.service;

import java.util.List;
import java.util.Map;

/**
 * @Author 小浩
 * @Date 2019/11/28 21:41
 * @Version 1.0
 **/
public interface ItemSearchService {
    /**
     * 搜索
     */
    public Map<String, Object> search(Map searchMap);

    /**
     * 导入
     *
     * @param list
     */
    public void importList(List list);

    public void deleteByGoodsIds(List goodsIdList);

}
