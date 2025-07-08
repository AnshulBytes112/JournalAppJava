package com.anshul.journalApp1.service;


import com.anshul.journalApp1.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        assertEquals(4, 2 + 2);
        assertNotNull(userRepository.findByUsername("lili"));
    }

    @ParameterizedTest
    @CsvSource({
            "anshul",
            "lily",
            "rahul"
    })
    public void testFindByParameter(String name) {
        Assertions.assertNotNull(userRepository.findByUsername(name), "User not found: " + name);
    }
}

