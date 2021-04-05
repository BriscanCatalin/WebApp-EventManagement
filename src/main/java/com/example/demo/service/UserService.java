package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.models.dto.AdminRegistrationDto;
import com.example.demo.models.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User save(UserRegistrationDto registration);
    User saveAdmin(AdminRegistrationDto registration);

}
