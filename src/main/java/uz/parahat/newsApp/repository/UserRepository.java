package uz.parahat.newsApp.repository;

import org.springframework.data.repository.CrudRepository;
import uz.parahat.newsApp.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByPhone(String phone);

	Optional<User> findByToken(String token);
}
