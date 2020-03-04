package com.example.product.service;

import com.example.product.common.DecreaseStockInput;
import com.example.product.domain.ProductInfo;

import java.util.List;

/**
 * @Author : FuYu
 * @Description :
 */
public interface ProductService {
    /**
     * 查询所有上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDTOList
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
