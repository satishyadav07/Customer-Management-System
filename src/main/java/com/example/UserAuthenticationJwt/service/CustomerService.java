package com.example.UserAuthenticationJwt.service;

import com.example.UserAuthenticationJwt.entity.Customer;
import com.example.UserAuthenticationJwt.exception.CustomerException;
//import com.example.UserAuthenticationJwt.exception.CustomerException;

import java.util.List;

public interface CustomerService {

    public Customer registerCustomer(Customer customer);


    public Customer getCustomerDetailsByEmail(String email)throws CustomerException;

    public List<Customer> getAllCustomerDetails()throws CustomerException;
}
