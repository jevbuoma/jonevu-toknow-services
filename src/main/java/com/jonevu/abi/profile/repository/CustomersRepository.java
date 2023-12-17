package com.jonevu.abi.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonevu.abi.profile.model.Customers;

public interface CustomersRepository  extends JpaRepository<Customers, Long>{
}
