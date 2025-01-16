package com.example.UserAuthenticationJwt.service;

import com.example.UserAuthenticationJwt.Repository.CustomerRepository;
import com.example.UserAuthenticationJwt.entity.Customer;
import com.example.UserAuthenticationJwt.model.CustomerDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDetailsService  implements UserDetailsService {

    private CustomerRepository customerRepository;
    public  CustomerDetailsService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<Customer> opCustomer=customerRepository.findByEmail(username);

       if (opCustomer.isPresent()){
           Customer customer=opCustomer.get();

           List<GrantedAuthority> authorities=new ArrayList<>();
           return new CustomerDetails(customer);
       }else{
           throw new BadCredentialsException("User Details not found with this username "+username);
       }

    }
}
