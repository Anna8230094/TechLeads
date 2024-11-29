package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/hireandgo/registrationform")
public class RegistrationFormController {
    
    @GetMapping("/")
    public String registrationControl() {
        return "registrationform";
    }

}
