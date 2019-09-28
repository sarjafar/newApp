package uz.parahat.newsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.parahat.newsApp.domain.Role;
import uz.parahat.newsApp.domain.User;
import uz.parahat.newsApp.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping
	public String userList(Model model){
		model.addAttribute("users", userService.findAll());
		return "userlist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{user}")
	public String userEditForm(@PathVariable User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("roles", Role.values());

		return "userEdit";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public String userSave(
			@RequestParam String username,
			@RequestParam Map<String, String> form,
			@RequestParam("userId") User user
	) {
		userService.saveUser(user, username, form);

		return "redirect:/user";
	}

}
