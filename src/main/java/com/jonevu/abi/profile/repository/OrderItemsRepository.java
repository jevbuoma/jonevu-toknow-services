package com.jonevu.abi.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonevu.abi.profile.model.OrderItems;

public interface OrderItemsRepository  extends JpaRepository<OrderItems, Long>{
}
