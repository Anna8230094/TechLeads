package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hireandgo/home/registrationform/")
public class SuccessController {

    @GetMapping("/success/{id}")
    public String handleSuccess(Model model, @PathVariable(value = "id") Long id) {
        return "success";
    }

}
