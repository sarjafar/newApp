package uz.parahat.newsApp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.parahat.newsApp.domain.Article;
import uz.parahat.newsApp.domain.User;
import uz.parahat.newsApp.repository.ArticleRepository;
import uz.parahat.newsApp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private UserRepository userRepository;


	public ResponseEntity<List<Article>> listAll(String token) {
		Optional<User> userFromDb = userRepository.findByToken(token);
		if (userFromDb.isPresent()) {
			return new ResponseEntity<>((List<Article>) articleRepository.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<Article> getOne(Long articleId, String token) {
		Optional<User> userFromDb = userRepository.findByToken(token);
		if (userFromDb.isPresent()) {
			Optional<Article> articleOptional = articleRepository.findById(articleId);
			if (articleOptional.isPresent()) {
				return new ResponseEntity(articleOptional.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<Article> create(Article article, String token) {
		Optional<User> userFromDb = userRepository.findByToken(token);
		if (userFromDb.isPresent()) {
			article.setCreatedDate(LocalDateTime.now());
			article.setAuthor(userFromDb.get());
			articleRepository.save(article);
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity update(Long articleId, Article article, String token) {
		Optional<User> userFromDb = userRepository.findByToken(token);
		if (userFromDb.isPresent()) {
			Optional<Article> articleFromDb = articleRepository.findById(articleId);
			if (articleFromDb.isPresent()) {
				if (articleFromDb.get().getAuthor().getId().equals(userFromDb.get().getId())) {
					BeanUtils.copyProperties(article, articleFromDb.get(), "id");
					articleRepository.save(articleFromDb.get());
					return new ResponseEntity(HttpStatus.OK);
				} else {
					return new ResponseEntity(HttpStatus.BAD_REQUEST);

				}
			} else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity delete(Long articleId, String token) {
		Optional<Article> articleFromDb = articleRepository.findById(articleId);
		if (articleFromDb.isPresent()) {
			Optional<Article> articleOptional = articleRepository.findById(articleId);
			if (articleOptional.isPresent()) {
				articleRepository.deleteById(articleId);
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}
}
