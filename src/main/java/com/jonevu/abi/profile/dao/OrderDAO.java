package com.jonevu.abi.profile.dao;


import com.jonevu.abi.profile.model.Orders;

import java.util.List;

public interface OrderDAO {
	
    public Iterable<Orders> findAll();

    public Orders findAOrderById(Long id);

    public Orders createOrder(Orders order);

    public Long countOrders();

    public void deleteOrder(Long id);

    public void deleteOrder(Orders order);

    public Iterable<Orders> addOrders(Iterable<Orders> order);

    public Orders addOrder(Orders order);

    public List<Orders> findCustomerIdOrderMode(Long customerId, String orderMode);

    public List<Orders> fetchOrders(Long customerId);
}
