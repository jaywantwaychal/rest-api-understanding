package com.jdsolutions.restful_web.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class UserJPAResource {
	
	@Autowired
	private UserJPARepository userJPARepository;
	
	@Autowired
	private UserPostRepository postRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userJPARepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") int id) {
		Optional<User> user = userJPARepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not Found: Id = " + id);
		}
		return user.get();
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User savedUser = userJPARepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable ("id") int id) {
		User user = getUser(id);
		userJPARepository.deleteById(id);
		return user;
	}
	
	@GetMapping("/users/posts")
	public List<Post> getAllUserPosts() {
		return postRepository.findAll();
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> getUserPosts(@PathVariable("id")int id) {
		User user = getUser(id);
		return user.getPosts();
	}
	

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> addUserPost(@PathVariable("id") int id, @RequestBody Post post) {
		User user = getUser(id);
		post.setUser(user);
		Post postsaved = postRepository.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(postsaved.getId())
			.toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
