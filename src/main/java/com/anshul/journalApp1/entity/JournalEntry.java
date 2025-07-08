package com.anshul.journalApp1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Builder

//the above all are included in one annotation @data

@Document(collection="journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    //the main objective of using objectid not string or int is it automatically maintains uniqueness and identifiable by mongodb
    //we do not use string because mongodb by default uses objectid

    @JsonIgnore
    @Id
    private ObjectId id;
    private LocalDateTime date;
    @NonNull
    private String title;
    private String content;
}
