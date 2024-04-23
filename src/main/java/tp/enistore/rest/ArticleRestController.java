package tp.enistore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.bo.Article;
import tp.enistore.service.ArticleService;

@RestController
@RequestMapping("/api/v1")
public class ArticleRestController {

	@Autowired
	ArticleService articleService; 
	
	@GetMapping("/articles")
	public List<Article> getArticles() {
		return articleService.getArticles();
	}
	
	@GetMapping("/article/{id}")
	public Article getArticleById(@PathVariable("id") Long id) {
		return articleService.getArticleById(id);
	}
	
	@GetMapping("/delete-article/{id}")
	public Boolean deleteById(@PathVariable("id") Long id) {
		articleService.deleteArticle(id);
		return true;
	}
}
