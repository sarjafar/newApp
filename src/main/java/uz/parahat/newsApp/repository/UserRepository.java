package uz.parahat.newsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.parahat.newsApp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByActivationCode(String code);
}
