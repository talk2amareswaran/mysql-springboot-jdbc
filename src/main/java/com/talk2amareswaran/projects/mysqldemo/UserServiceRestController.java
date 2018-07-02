package com.talk2amareswaran.projects.mysqldemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceRestController {

	@Autowired
	UserServiceDAO userServiceDAO;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<Users> getUsers() {
		return userServiceDAO.getUsers();
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String insertUser(@RequestBody Users user) {
		userServiceDAO.createUser(user);
		return "User created successfully";
	}
}
