package com.jonevu.abi.profile.service;


import java.util.List;

import com.jonevu.abi.profile.model.Orders;

public interface OrderService {

    List<Orders> listAll();

    Orders getById(Long id);

    Orders saveOrUpdate(Orders order);

    void delete(Long id);
}
