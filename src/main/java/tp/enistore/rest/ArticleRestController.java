package tp.enistore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.bo.Article;
import tp.enistore.service.ArticleService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ArticleRestController {

	@Autowired
	ArticleService articleService; 
	
	@GetMapping("/articles")
	public List<Article> getArticles() {
		return articleService.getArticles_Deprecated();
	}
	
	@GetMapping("/article/{uid}")
	public Article getArticleById(@PathVariable("uid") String uid) {
		return articleService.getArticleById_Deprecated(uid);
	}
	
	@DeleteMapping("/delete-article/{uid}")
	public Boolean deleteById(@PathVariable("uid") String uid) {
		articleService.deleteArticle_Deprecated(uid);
		return true;
	}
	
	@PostMapping("/add-article")
	public Boolean addArticle(@RequestBody Article article) {
		articleService.saveArticle_Deprecated(article);
		return true;
	}
	
	@PatchMapping("/edit-article")
	public Boolean editArticle(@RequestBody Article article) {
		articleService.saveArticle_Deprecated(article);
		return true;
	}

}
