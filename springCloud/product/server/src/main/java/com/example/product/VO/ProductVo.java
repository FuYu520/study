package com.example.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : FuYu
 * @Despriotion :
 */
@Data
public class ProductVo {

    /**
     * 类目名称
     *  @JsonProperty("name") 给前端看的格式
     */
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVOList;
}
