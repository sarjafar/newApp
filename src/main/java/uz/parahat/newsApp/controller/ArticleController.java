package uz.parahat.newsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.parahat.newsApp.domain.Article;
import uz.parahat.newsApp.repository.ArticleRepository;
import uz.parahat.newsApp.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	public ArticleController(ArticleRepository articleRepository) {
	}

	@GetMapping
	public ResponseEntity<List<Article>> list(@RequestHeader("token") String token) {
		return articleService.listAll(token);
	}

	@GetMapping("{id}")
	public ResponseEntity<Article> getOne(
			@PathVariable("id") Long articleId,
			@RequestHeader("token") String token
	) {
		return articleService.getOne(articleId, token);
	}

	@PostMapping
	public ResponseEntity<Article> create(
			@RequestBody Article article,
			@RequestHeader("token") String token
	) {
		return articleService.create(article, token);
	}

	@PutMapping("{id}")
	public ResponseEntity update(
			@PathVariable("id") Long articleId,
			@RequestBody Article article,
			@RequestHeader("token") String token
	) {
		return articleService.update(articleId, article, token);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(
			@PathVariable("id") Long articleId,
			@RequestHeader("token") String token
	) {
		return articleService.delete(articleId, token);
	}

}
