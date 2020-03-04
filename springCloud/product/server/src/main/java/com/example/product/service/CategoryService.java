package com.example.product.service;

import com.example.product.domain.ProductCategory;

import java.util.List;

/**
 * @Author : FuYu
 * @Despriotion :
 */
public interface CategoryService {
    /**
     * 根据类名查询
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
