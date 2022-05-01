package com.laiyw.micro.stock.api.client;

import com.laiyw.micro.stock.api.vo.CommodityVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/30 13:05
 * @Description TODO
 */

@FeignClient(value = "stock", path = "/commodity")
public interface CommodityClient {

    /**
     * 根据商品ID获取商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getCommodityInfoById")
    CommodityVo getCommodityInfoById(Long id);

    /**
     * 库存锁定
     *
     * @param id
     * @param number
     * @return
     */
    @GetMapping("/updateCommodityStock")
    boolean updateCommodityStock(@RequestParam("id") Long id, @RequestParam("number") Long number);
}
