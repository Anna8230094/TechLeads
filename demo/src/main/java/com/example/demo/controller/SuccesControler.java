package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.database.user.UserRepository;

@Controller
@RequestMapping("/hireandgo/home/registrationform/success")
public class SuccesControler {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public void  handleSucces(Model model, @RequestParam String username, @PathVariable Long id) {
        if(userRepository.findById(id) == null)
        throw new Error();
        model.addAttribute("username", username);

    }

}
