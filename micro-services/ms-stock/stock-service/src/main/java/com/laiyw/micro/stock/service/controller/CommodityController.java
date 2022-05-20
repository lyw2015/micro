package com.laiyw.micro.stock.service.controller;

import com.laiyw.micro.common.controller.BaseController;
import com.laiyw.micro.common.domain.AjaxResult;
import com.laiyw.micro.common.utils.BeanUtils;
import com.laiyw.micro.stock.api.client.CommodityClient;
import com.laiyw.micro.stock.api.vo.CommodityVo;
import com.laiyw.micro.stock.service.domain.Commodity;
import com.laiyw.micro.stock.service.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/30 13:02
 * @Description CommodityController
 */

@RestController
@RequestMapping("/commodity")
public class CommodityController extends BaseController implements CommodityClient {

    @Autowired
    private ICommodityService commodityService;

    @GetMapping("/listCommodity")
    public AjaxResult listCommodity() {
        return AjaxResult.success(commodityService.listCommodity());
    }

    @Override
    public CommodityVo getCommodityInfoById(Long id) {
        Commodity commodity = commodityService.getCommodityById(id);
        return BeanUtils.createCopy(commodity, CommodityVo.class);
    }

    @Override
    public boolean updateCommodityStock(Long id, Long number) {
        return commodityService.updateCommodityStock(id, number);
    }

    @GetMapping("randomCommodity")
    public AjaxResult randomCommodity() {
        return AjaxResult.success(commodityService.randomCommodity());
    }

    @GetMapping("/removeCommodityById")
    public AjaxResult removeCommodityById(Long id) {
        commodityService.removeCommodityById(id);
        return success();
    }
}
