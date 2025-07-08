package com.anshul.journalApp1.controller;

import com.anshul.journalApp1.entity.User;
import com.anshul.journalApp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public void createuser(@RequestBody User user){
        userService.savenewentry(user);

    }

    @GetMapping("/health-check")
    public String healthcehck(){
        return "OK";
    }
}
