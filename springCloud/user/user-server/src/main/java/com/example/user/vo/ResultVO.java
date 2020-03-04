package com.example.user.vo;

import lombok.Data;

/**
 * @Author : FuYu
 * @Description :
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
