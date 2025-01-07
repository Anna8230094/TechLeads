package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hireandgo/home/registrationform/")
public class SuccessController {

    @GetMapping("/success/{id}")
    public String handleSuccess(Model model, @RequestParam String username, @PathVariable(value = "id") Long id) {
        return "success";
    }

}
