package com.jdsolutions.restful_web.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserServiceDao {
	
	public static List<User> users = new ArrayList<>();
	public static int userCounter = 0;
	
	static {
		users.add(new User(++userCounter, "JK", new Date()));
		users.add(new User(++userCounter, "JK1", new Date()));
		users.add(new User(++userCounter, "JK2", new Date()));
	}

	public List<User> getAllUsers() {
		return users;
	}
	
	public User saveUser(User user) {
		user.setId(++userCounter);
		users.add(user);
		return user;
	}
	
	public User getUser(int id) {
		return users.stream()
				.filter(user -> user.getId() == id)
				.findFirst()
				.orElse(null);
	}
}
