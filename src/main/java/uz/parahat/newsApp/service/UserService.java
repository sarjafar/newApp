package uz.parahat.newsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.parahat.newsApp.domain.Role;
import uz.parahat.newsApp.domain.User;
import uz.parahat.newsApp.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailSender emailSender;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			System.out.println("User not found!");
		}
		return user;
	}

	public boolean addUser(User user) {
		User userFromDb = userRepository.findByUsername(user.getUsername());
		if (user == null) {
			return false;
		}

		user.setActive(true);
		user.setRoles(Collections.singleton(Role.EDITOR));
		user.setActivationCode(UUID.randomUUID().toString());
		user.setPassword(user.getPassword());

		userRepository.save(user);

		sendMessage(user);

		return true;

	}

	private void sendMessage(User user) {
		if (!StringUtils.isEmpty(user.getEmail())) {
			String message = String.format(
					"Hello, %s! \n " +
							"Please, visit next link: http://localhost:8080/activate/%s",
					user.getUsername(),
					user.getActivationCode()
			);

			emailSender.send(user.getEmail(), "Activation code", message);
		}
	}

	public boolean activateUser(String code) {
		User user = userRepository.findByActivationCode(code);

		if (user == null) {
			return false;
		}

		user.setActivationCode(null);
		userRepository.save(user);
		return true;
	}

	public void saveUser(User user, String username, Map<String, String> forma) {
		user.setUsername(username);

		Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
		user.getRoles().clear();

		for (String key : forma.keySet()) {
			if (roles.contains(key)) {
				user.getRoles().add(Role.valueOf(key));
			}
		}

		userRepository.save(user);
	}


	public List<User> findAll() {
		return userRepository.findAll();
	}
}
