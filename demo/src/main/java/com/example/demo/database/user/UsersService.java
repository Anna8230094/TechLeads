package com.example.demo.database.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public Users saveUsers(Users users) {
        return userRepository.save(users);
    }
} 

