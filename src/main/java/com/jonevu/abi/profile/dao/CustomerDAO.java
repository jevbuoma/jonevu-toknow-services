package com.jonevu.abi.profile.dao;


import com.jonevu.abi.profile.model.Customers;

import java.util.List;

public interface CustomerDAO {
	
    public Iterable<Customers> findAll();

    public Customers findAOrderById(Long id);

    public Customers createCustomers(Customers customers);

    public Long countCustomers();

    public void deleteCustomer(Long id);

    public void deleteCustomer(Customers customer);

    public Iterable<Customers> addCustomers(Iterable<Customers> customers);

    public Customers addCustomer(Customers customers);
}
