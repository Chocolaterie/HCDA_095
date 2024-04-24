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
	
	@GetMapping("/article/{uid}")
	public Article getArticleById(@PathVariable("uid") String uid) {
		return articleService.getArticleById(uid);
	}
	
	@GetMapping("/delete-article/{uid}")
	public Boolean deleteById(@PathVariable("uid") String uid) {
		articleService.deleteArticle(uid);
		return true;
	}
}
