package com.example.order.service;

import com.example.order.dto.OrderDTO;

/**
 * @Author : FuYu
 * @Description :
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
