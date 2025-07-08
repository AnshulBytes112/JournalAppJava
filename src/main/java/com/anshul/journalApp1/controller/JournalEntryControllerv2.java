package com.anshul.journalApp1.controller;

import com.anshul.journalApp1.entity.JournalEntry;
import com.anshul.journalApp1.entity.User;
import com.anshul.journalApp1.service.JournalEntryService;
import com.anshul.journalApp1.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")

public class JournalEntryControllerv2 {
    @Autowired
    private JournalEntryService journalentryservice;
    @Autowired
    private UserService userservice;
    private Map<Long, JournalEntry> journalentries=new HashMap<>();

    @GetMapping
    public  ResponseEntity<?> getAll(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user=userservice.findByUsername(username);
        List<JournalEntry> list=user.getJournalentries();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createntry(@RequestBody JournalEntry myentry){
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username= authentication.getName();


            journalentryservice.saveentry(myentry,username);
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace(); // ✅ Logs the actual error
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getjournalentrybyid(@PathVariable ObjectId myId){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user = userservice.findByUsername(username);
        List<JournalEntry> collect = user.getJournalentries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalentry=journalentryservice.findbyid(myId);
            if(journalentry.isPresent()){
                return new ResponseEntity<>(journalentry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteentrybyid(@PathVariable ObjectId myId){
        //? denoted wildcard we can pass any object without restriction ogf having only jou
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        journalentryservice.deletebyid(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updatejourneybyid(@PathVariable ObjectId id, @RequestBody JournalEntry newentry){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user=userservice.findByUsername(username);

        List<JournalEntry> collect = user.getJournalentries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalentry=journalentryservice.findbyid(id);
            if(journalentry.isPresent()){
                JournalEntry old=journalentryservice.findbyid(id).orElse(null);

                if(old!=null) {
                    old.setTitle(!newentry.getTitle().isEmpty() ? newentry.getTitle() : old.getTitle());
                    old.setContent(newentry.getContent() != null && !newentry.getContent().isEmpty() ? newentry.getContent() : old.getContent());
                    journalentryservice.saveentry(old);
                    return new ResponseEntity<>(old,HttpStatus.OK);
                } else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }
}

