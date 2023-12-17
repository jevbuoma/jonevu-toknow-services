package com.jonevu.abi.profile.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jonevu.abi.profile.model.Orders;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long>{
    List<Orders> findByOrderMode(String orderMode);
    List<Orders> findByCustomerIdAndOrderMode(Long customerId, String orderMode);
    @Query("SELECT a FROM Orders a WHERE a.customerId=:customerId")
    List<Orders> fetchOrders(@Param("customerId") Long customerId);
}
