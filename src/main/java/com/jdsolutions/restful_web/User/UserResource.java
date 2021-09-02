package com.jdsolutions.restful_web.User;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserServiceDao userServiceDao;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userServiceDao.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") int id) {
		User user = userServiceDao.getUser(id);	
		if(null == user) {
			throw new UserNotFoundException("User Not Found: Id = " + id);
		}
		return user;
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User savedUser = userServiceDao.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	

}
