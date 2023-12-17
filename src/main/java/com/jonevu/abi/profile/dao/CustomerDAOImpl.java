package com.jonevu.abi.profile.dao;

import com.jonevu.abi.profile.model.Customers;
import com.jonevu.abi.profile.repository.CustomersRepository;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private CustomersRepository customersRepository;

    public CustomerDAOImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public Iterable<Customers> findAll() {
        return customersRepository.findAll();
    }

    @Override
    public Customers findAOrderById(Long id) {
        return customersRepository.findOne(id);
    }

    @Override
    public Customers createCustomers(Customers customers) {
        return customersRepository.save(customers);
    }

    @Override
    public Long countCustomers() {
        return customersRepository.count();
    }

    @Override
    public void deleteCustomer(Long id) {
        customersRepository.delete(id);
    }

    @Override
    public void deleteCustomer(Customers customer) {
        customersRepository.delete(customer);
    }

    @Override
    public Iterable<Customers> addCustomers(Iterable<Customers> customers) {
        return customersRepository.save(customers);
    }

    @Override
    public Customers addCustomer(Customers customer) {
        return customersRepository.save(customer);
    }
}
