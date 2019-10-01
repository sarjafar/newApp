package uz.parahat.newsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.parahat.newsApp.domain.User;
import uz.parahat.newsApp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> list() {
		return userService.findAll();
	}

	@GetMapping("{id}")
	public User getOne(@PathVariable("id") User user) {
		return user;
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		return userService.register(user);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		return userService.login(user);
	}
}
