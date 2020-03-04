package com.example.product.util;

import com.example.product.VO.ResultVo;

/**
 * @Author : FuYu
 * @Description :
 */
public class ResultVOUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }
}
