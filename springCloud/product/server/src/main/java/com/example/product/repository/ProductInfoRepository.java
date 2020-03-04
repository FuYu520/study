package com.example.product.repository;

import com.example.product.domain.ProductInfo;
import com.example.product.enums.ProductStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : FuYu
 * @Despriotion :
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer code);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
