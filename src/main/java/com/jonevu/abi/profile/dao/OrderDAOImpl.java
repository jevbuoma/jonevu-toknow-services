package com.jonevu.abi.profile.dao;

import org.springframework.stereotype.Repository;

import com.jonevu.abi.profile.model.Orders;
import com.jonevu.abi.profile.repository.OrdersRepository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private OrdersRepository ordersRepository;

    public OrderDAOImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Iterable<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders findAOrderById(Long id) {
        return ordersRepository.findOne(id);
    }

    @Override
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public Long countOrders() {
        return ordersRepository.count();
    }

    @Override
    public void deleteOrder(Long id) {
        ordersRepository.delete(id);
    }

    @Override
    public void deleteOrder(Orders order) {
        ordersRepository.delete(order);
    }

    @Override
    public Iterable<Orders> addOrders(Iterable<Orders> orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders addOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public List<Orders> findCustomerIdOrderMode(Long customerId, String orderMode) {
        return ordersRepository.findByCustomerIdAndOrderMode(customerId, orderMode);
    }

    @Override
    public List<Orders> fetchOrders(Long customerId) {
        return ordersRepository.fetchOrders(customerId);
    }
}
