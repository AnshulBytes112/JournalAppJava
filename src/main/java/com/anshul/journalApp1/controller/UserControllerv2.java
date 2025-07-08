package com.anshul.journalApp1.controller;

import com.anshul.journalApp1.entity.User;
import com.anshul.journalApp1.repository.UserRepository;
import com.anshul.journalApp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserControllerv2 {
    @Autowired
    private UserRepository userrepository;

    //to control user data we need services of mongodb which is present in service rep
    @Autowired
    private UserService userservice;

    @GetMapping
    public List<User> getAll(){
        return userservice.getAll();
    }




    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user2=userservice.findByUsername(username);
        user2.setUsername(user.getUsername());
        user2.setPassword(user.getPassword());//this is just updated but not saved yet
        userservice.savenewentry(user2);//now the updated user os saved

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping
    public ResponseEntity<?> DeleteEntrybyId(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        userrepository.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}

