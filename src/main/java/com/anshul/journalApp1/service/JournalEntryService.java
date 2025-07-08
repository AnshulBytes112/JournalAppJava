package com.anshul.journalApp1.service;

import com.anshul.journalApp1.entity.JournalEntry;
import com.anshul.journalApp1.entity.User;
import com.anshul.journalApp1.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
    //here all services or logic fucntions are build like crud operations
    @Autowired
    private JournalEntryRepository journalentryrepository;
    @Autowired
    private UserService userservice;


    public void saveentry(JournalEntry journalentry, String  username){
        try{
            User user=userservice.findByUsername(username);
            JournalEntry saved=journalentryrepository.save(journalentry);
            user.getJournalentries().add(saved);
            userservice.saveentry(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveentry(JournalEntry journalentry){
        journalentryrepository.save(journalentry);
    }

    public List<JournalEntry> getAll(){
        return journalentryrepository.findAll();
    }


    public void deletebyid(ObjectId id, String username){
        try {
            User user = userservice.findByUsername(username);
            boolean b = user.getJournalentries().removeIf(x -> x.getId().equals(id));
            if (b) {
                userservice.saveentry(user);
                journalentryrepository.deleteById(id);
            }
            //the above command is a lambda expression and returns true if id is removed... the journal entries mentioned in the user data if the id is equal
            //to the given id
            //this just updates the user but note this point it is very important
            //the user is not saved we have to manually save it
        } catch (Exception e) {
            log.error("error occured for"+username);
            throw new RuntimeException("Error occured while deleting the id");
        }

    }

    public Optional<JournalEntry> findbyid(ObjectId myId) {

        Optional<JournalEntry> byId = journalentryrepository.findById(myId);
        return byId;

    }


}
