package com.rps.controller;

import com.rps.entity.User;
import com.rps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HtmlController {
    @GetMapping("/hello")
    public String okay(Model model){
        model.addAttribute("username", "lol");
        return "okkk/index";
    }
}
