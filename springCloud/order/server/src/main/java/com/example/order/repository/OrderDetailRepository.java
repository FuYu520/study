package com.example.order.repository;


import com.example.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : FuYu
 * @Description :
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
