package com.anshul.journalApp1.entity;

import lombok.*;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Builder

//the above all are included in one annotation @data

@Document(collection="user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //the main objective of using objectid not string or int is it automatically maintains uniqueness and identifiable by mongodb
    //we do not use string because mongodb by default uses objectid

    @Id
    private ObjectId id;
//by defulat indexing is not done by springboot we have to ask it to do in application.properties

    @Indexed(unique = true)    //makes searching for email name faster and checks that each username should be unique
    @NonNull //so if a user enter null then nullpointerexception will be thrown
    private String username;
    @NonNull
    private String password;

    @DBRef  //creating a reference to collection named JournalEntry connects both collection
    //but it will not show data but will show that the number of references it has along with theire object id
    //that can help to access its detail
    private List<JournalEntry> journalentries=new ArrayList<>(); //this is connected to journal entry now
    private List<String> roles;
}
