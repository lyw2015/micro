package com.laiyw.micro.stock.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laiyw.micro.stock.service.domain.Commodity;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/30 10:26
 * @Description TODO
 */

public interface ICommodityService extends IService<Commodity> {

    /**
     * 随机保存商品信息
     *
     * @return
     */
    Commodity randomCommodity();

    /**
     * 根据ID获取库存
     *
     * @param id
     * @return
     */
    Commodity getCommodityById(Long id);


    /**
     * 获取商品列表
     *
     * @return
     */
    List<Commodity> listCommodity();

    /**
     * 跟新库存
     *
     * @param commodity
     * @return
     */
    Commodity updateCommodity(Commodity commodity);

    /**
     * 更新库存
     *
     * @param id
     * @param number
     * @return
     */
    boolean updateCommodityStock(Long id, Long number);

    /**
     * 根据ID删除库存
     *
     * @param id
     * @return
     */
    boolean removeCommodityById(Long id);
}
