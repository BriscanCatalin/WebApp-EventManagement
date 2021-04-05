package com.example.demo.controller;

import com.example.demo.models.Admin;
import com.example.demo.models.dto.AdminRegistrationDto;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registrationAdmin")
public class AdminRegistrationController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @ModelAttribute("admin")
    public AdminRegistrationDto adminRegistrationDto() {
        return new AdminRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        return "registrationAdmin";
    }

    @PostMapping
    public String registerAdminAccount(@ModelAttribute("admin") @Valid AdminRegistrationDto adminDto, BindingResult result) {
        Admin existing = adminService.findByEmail(adminDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email!");
        }

        if (result.hasErrors()) {
            return "registrationAdmin";
        }
        if (adminDto.getEmail().contains("@admin.com")) {
            userService.saveAdmin(adminDto);
            adminService.save(adminDto);
            return "redirect:/registrationAdmin?success";
        }
        return "redirect:/registrationAdmin";
    }
}
