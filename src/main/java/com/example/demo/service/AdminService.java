package com.example.demo.service;

import com.example.demo.models.Admin;
import com.example.demo.models.dto.AdminRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {

    Admin findByEmail(String email);
    Admin save(AdminRegistrationDto registration);

}
