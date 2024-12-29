package com.example.demo.database.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public CompletableFuture <Void> saveUsers(Users users) {
        userRepository.save(users);
        return CompletableFuture.completedFuture(null);
    }
} 
