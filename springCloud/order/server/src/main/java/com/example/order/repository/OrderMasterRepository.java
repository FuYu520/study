package com.example.order.repository;

import com.example.order.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author : FuYu
 * @Description :
 */
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    Optional<OrderMaster> findByOrderId(String orderId);

}
