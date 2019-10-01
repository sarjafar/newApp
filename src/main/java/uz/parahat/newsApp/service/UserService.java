package uz.parahat.newsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.parahat.newsApp.domain.User;
import uz.parahat.newsApp.repository.UserRepository;
import uz.parahat.newsApp.utils.MyUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
@Autowired
private UserRepository userRepository;

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public ResponseEntity<User> register(User user) {
		Optional<User> userFromDb = userRepository.findByPhone(user.getPhone());
		if (userFromDb.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			user.setToken(MyUtils.generateNewToken());
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
		}
	}

	public ResponseEntity<User> login(User user) {
		Optional<User> userFromDb = userRepository.findByPhone(user.getPhone());
		if (userFromDb.isPresent()) {
			user.setToken(MyUtils.generateNewToken());
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
