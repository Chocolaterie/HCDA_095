package tp.enistore.rest.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.bo.Article;
import tp.enistore.bo.ServiceResponse;
import tp.enistore.service.ArticleService;

@RestController()
@RequestMapping("/api/v2")
@CrossOrigin
public class ArticleRestControllerV2 {

	@Autowired
	ArticleService articleService; 
	
	@GetMapping("/articles")
	public ServiceResponse<List<Article>> getArticles() {
		return articleService.getArticles();
	}
	
	@GetMapping("/article/{uid}")
	public ServiceResponse<Article> getArticleById(@PathVariable("uid") String uid) {
		return articleService.getArticleById(uid);
	}
	
	@PostMapping("/add-article")
	public ServiceResponse<Article> addArticle(@RequestBody Article article) {
		return articleService.saveArticle(article);
	}
	
	@PatchMapping("/edit-article")
	public ServiceResponse<Article> editArticle(@RequestBody Article article) {
		return articleService.saveArticle(article);
	}
	
}
