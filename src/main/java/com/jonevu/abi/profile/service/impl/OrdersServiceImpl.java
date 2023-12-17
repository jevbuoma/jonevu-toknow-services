package com.jonevu.abi.profile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonevu.abi.profile.model.Orders;
import com.jonevu.abi.profile.repository.OrdersRepository;
import com.jonevu.abi.profile.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrderService {

    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRespository) {
        this.ordersRepository = ordersRespository;
    }

    @Override
    public List<Orders> listAll() {
        List<Orders> orderList = new ArrayList<>();
        ordersRepository.findAll().forEach(orderList::add);
        return orderList;
    }

    @Override
    public Orders getById(Long id) {
        return ordersRepository.findOne(id);
    }

    @Override
    public Orders saveOrUpdate(Orders order) {
        ordersRepository.save(order);
        return order;
    }

    @Override
    public void delete(Long id) {
        ordersRepository.delete(id);
    }
}
