package com.example.demo.service;

import com.example.demo.dataRepository.AdminRepository;
import com.example.demo.models.Admin;
import com.example.demo.models.Role;
import com.example.demo.models.dto.AdminRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Admin findByEmail(String email){
        return adminRepository.findByEmail(email);
    }

    public Admin save(AdminRegistrationDto registration){
        Admin admin = new Admin();
        admin.setFirstName(registration.getFirstName());
        admin.setLastName(registration.getLastName());
        admin.setEmail(registration.getEmail());
        admin.setPassword(passwordEncoder.encode(registration.getPassword()));
        admin.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return adminRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(admin.getEmail(),
                admin.getPassword(),
                mapRolesToAuthorities(admin.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
