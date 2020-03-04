package com.example.product.controller;

import com.example.product.common.DecreaseStockInput;
import com.example.product.domain.ProductCategory;
import com.example.product.domain.ProductInfo;
import com.example.product.service.CategoryService;
import com.example.product.service.ProductService;
import com.example.product.VO.ProductInfoVo;
import com.example.product.VO.ProductVo;
import com.example.product.VO.ResultVo;
import com.example.product.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : FuYu
 * @Despriotion :
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 查询所有在架的商品
     * 查询类目type列表
     * 查询类目
     * 构造数据
     */
    @GetMapping("list")
    //allowCredentials 允许cookie跨域
    @CrossOrigin(allowCredentials = "true")
    public ResultVo<ProductVo> list(){
        //查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //查询类目type列表
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        //从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVo> productVoList = new ArrayList<>();
        //构造数据
        for (ProductCategory productCategory : categoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVOList(productInfoVoList);
            productVoList.add(productVo);
        }


        return ResultVOUtil.success(productVoList);
    }

    /**
     * 获取商品列表
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList){
        /**
         *  order Hystrix 超时设置
         */
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<ProductInfo> list = productService.findList(productIdList);
        return list;
    }

    /**
     * 扣库存
     * @param cartDTOList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList){
        productService.decreaseStock(cartDTOList);
    }
}
