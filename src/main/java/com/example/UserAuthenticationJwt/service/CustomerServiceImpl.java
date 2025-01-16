package com.example.UserAuthenticationJwt.service;

import com.example.UserAuthenticationJwt.Repository.CustomerRepository;
import com.example.UserAuthenticationJwt.entity.Customer;
//import com.example.UserAuthenticationJwt.exception.CustomerException;
import com.example.UserAuthenticationJwt.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{


    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer)    ;
    }

    @Override
    public Customer getCustomerDetailsByEmail(String email) throws CustomerException {
        return customerRepository.findByEmail(email).orElseThrow(()->new CustomerException("Customer not found with email "+email));
    }

    @Override
    public List<Customer> getAllCustomerDetails() throws CustomerException {
        List<Customer> customers=customerRepository.findAll();
        if(customers.isEmpty()){
            throw new CustomerException("No customer find");
        }
       return  customers;

    }
}
