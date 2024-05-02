package tp.enistore.rest.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.bo.Article;
import tp.enistore.bo.Category;
import tp.enistore.bo.FormRequest;
import tp.enistore.bo.ServiceResponse;
import tp.enistore.service.ArticleService;

@RestController()
@RequestMapping("/api/v2/admin")
@CrossOrigin
public class AdminRestController {

	@Autowired
	ArticleService articleService; 
	
	@DeleteMapping("/delete-article/{uid}")
	public ServiceResponse<Boolean> deleteById(@PathVariable("uid") String uid) {
		return articleService.deleteArticle(uid);
	}
	
	@PostMapping("/add-category")
	public ServiceResponse<Category> addCategory(@RequestBody Category category) {
		return articleService.saveCategory(category);
	}
	
	@PatchMapping("/edit-category")
	public ServiceResponse<Category> editCategory(@RequestBody Category category) {
		return articleService.saveCategory(category);
	}
	
	@PostMapping("/test-association")
	public ServiceResponse<Article> addCategory(@RequestBody FormRequest<Article, String> requestData) 
	{
		return articleService.associateCategory(requestData);
	}
}
