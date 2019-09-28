package uz.parahat.newsApp.repository;

import org.springframework.data.repository.CrudRepository;
import uz.parahat.newsApp.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
