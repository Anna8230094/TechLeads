/**
 * This class represents my class in Java.
 * 
 * <p>It is designed to demonstrate how to declare the author of a class
 * using a JavaDoc comment.</p>
 * 
 * @author Konstantia Stergiou
 * @version 1.0
 */

package com.example.demo.database.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Async
    public CompletableFuture<Void> saveUsers(Users users) {
        userRepository.save(users);
        System.out.println("The user is saved");
        return CompletableFuture.completedFuture(null);
    }

}


