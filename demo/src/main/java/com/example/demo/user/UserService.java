package com.example.demo.user;

import java.util.List;


import org.springframework.stereotype.Service;

@Service
public class UserService {
    public List<User> getUsers(){
		return List.of(
			new User(1L,"Maria","Papadopoulou","maria.jamal@gmail.com","Finance")
	    );
	}
}
