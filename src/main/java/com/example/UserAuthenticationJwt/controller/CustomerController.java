package com.example.UserAuthenticationJwt.controller;


import com.example.UserAuthenticationJwt.entity.Customer;
import com.example.UserAuthenticationJwt.model.CustomerDetails;
import com.example.UserAuthenticationJwt.service.CustomerDetailsService;
import com.example.UserAuthenticationJwt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private  CustomerService customerService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerController(CustomerService customerService,PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to spring Security";
    }


    @PostMapping("/customers")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
        String encPassword=passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encPassword);
        Customer cust=customerService.registerCustomer(customer);
        return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
    }


    @GetMapping("/Check-logged-in-user")
    public ResponseEntity<Customer> getLoggedInCustomerDetails(){
        CustomerDetails cd= (CustomerDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer=cd.getCustomer();
        return new ResponseEntity<Customer>(customer,HttpStatus.OK);

    }


    @GetMapping("/customer/{email}")
    public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable("email") String email){
        Customer customer=customerService.getCustomerDetailsByEmail(email);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomerHandler(){
        List<Customer> customers=customerService.getAllCustomerDetails();
        return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
    }


    @GetMapping("/signIn")
    public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
        System.out.println(auth);
        Customer customer=customerService.getCustomerDetailsByEmail(auth.getName());
        return new ResponseEntity<>(customer.getName()+" Logged In successfully", HttpStatus.ACCEPTED);
    }





}
