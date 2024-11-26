package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/hireandgo/index")
public class IndexController {

    @GetMapping("/")
    public String indexControl() {
        return "index";
    }

}
