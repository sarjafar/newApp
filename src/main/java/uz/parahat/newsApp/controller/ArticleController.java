package uz.parahat.newsApp.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.parahat.newsApp.domain.Article;
import uz.parahat.newsApp.repository.ArticleRepository;
import uz.parahat.newsApp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@RequestMapping("news")
@Api(tags = "Limits reference")
public class ArticleController {
	private UserRepository userRepository;
	private ArticleRepository articleRepository;

	@Autowired
	public ArticleController(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@GetMapping("/news")
	public List<Article> list() {
		return (List<Article>) articleRepository.findAll();
	}

	@GetMapping("/news/{id}")
	public Article getOne(@PathVariable("id") Article article) {
		return article;
	}

	@PostMapping
	public Article create(@RequestBody Article article) {
		article.setCreatedDate(LocalDateTime.now());
		return articleRepository.save(article);
	}

	@PutMapping("/news/{id}")
	public Article update(
			@PathVariable("id") Article articleFromDb,
			@RequestBody Article article
	) {
		BeanUtils.copyProperties(article, articleFromDb, "id");

		return articleRepository.save(articleFromDb);
	}

	@DeleteMapping("/news/{id}")
	public void delete(@PathVariable("id") Article article) {
		articleRepository.delete(article);
	}

}
