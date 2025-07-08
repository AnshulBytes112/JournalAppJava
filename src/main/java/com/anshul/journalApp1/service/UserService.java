package com.anshul.journalApp1.service;

import com.anshul.journalApp1.entity.User;
import com.anshul.journalApp1.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    //here all services or logic fucntions are build like crud operations
    @Autowired
    private UserRepository userrepository;

    private static final PasswordEncoder passwordencoder=new BCryptPasswordEncoder();

    public void savenewentry(User user){
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userrepository.save(user);

    }

    public void saveentry(User user){
        userrepository.save(user);

    }

    public List<User> getAll(){
        return userrepository.findAll();
    }

    public Optional<User> findbyid(ObjectId id){

        return userrepository.findById(id);
    }

    public User findByUsername(String username){
        return userrepository.findByUsername(username);
    }

    public void deletebyid(ObjectId id){

        userrepository.deleteById(id);
    }

    public void saveadmin(User user) {
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userrepository.save(user);
    }
}

