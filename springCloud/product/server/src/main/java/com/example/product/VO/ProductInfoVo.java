package com.example.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : FuYu
 * @Description :
 */
@Data
public class ProductInfoVo {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private BigDecimal productDescription;

    @JsonProperty("icon")
    private BigDecimal productIcon;
}
