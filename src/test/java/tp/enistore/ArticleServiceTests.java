package tp.enistore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tp.enistore.bo.Article;
import tp.enistore.bo.ServiceResponse;
import tp.enistore.helper.Constante;
import tp.enistore.service.ArticleService;

@SpringBootTest
@ActiveProfiles(value = {"mock"})
public class ArticleServiceTests {

	@Autowired
	ArticleService articleService;
	
	@Test
	public void test01() {
		// Appel service
		ServiceResponse<Article> response = articleService.getArticleById("1");
		
		// - 01
		assertEquals(response.code, Constante.CD_SUCCESS);
		
		// - 02
		assertNotNull(response.data);
	}
	
	@Test
	public void test02() {
		// Appel service
		ServiceResponse<Article> response = articleService.getArticleById("6");
		
		// - 01
		assertEquals(response.code, Constante.CD_ERR_NT_FOUND);
		
		// - 02
		assertNull(response.data);
	}
	
	@Test
	public void test03() {
		// Appel service
		ServiceResponse<Boolean> response = articleService.deleteArticle("6");
		
		// - 01
		assertEquals(response.code, Constante.CD_ERR_TCH);
		
		// - 02
		assertFalse(response.data);
	}
	
	@Test
	public void test04() {
		// Appel service 1
		ServiceResponse<Boolean> response = articleService.deleteArticle("2");
		
		// - 01
		assertEquals(response.code, Constante.CD_SUCCESS);
		
		// - 02
		assertTrue(response.data);
		
		// Appel service 2
		ServiceResponse<Article> response2 = articleService.getArticleById("2");
		
		// - 01
		assertEquals(response2.code, Constante.CD_ERR_NT_FOUND);
		
		// - 02
		assertNull(response2.data);
	}
	
	@Test
	public void test05() {
		// Appel service 1
		Article newArticle = new Article();
		newArticle.title = "Le nez de l'image d'Arthur";
		
		ServiceResponse<Article> response = articleService.saveArticle(newArticle);
		
		// - 01
		assertEquals(response.code, Constante.CD_SUCCESS);
		
		// - 02
		assertNotNull(response.data);
		
		// Appel service 2
		
		// -- uid généré dans le service lorsque l'article a été crée
		String uid = response.data.uid;
		
		// -- select l'article crée
		ServiceResponse<Article> response2 = articleService.getArticleById(uid);
		
		// - 01
		assertEquals(response2.code, Constante.CD_SUCCESS);
		
		// - 02
		assertNotNull(response2.data);
	}
	
	@Test
	public void test06() {
		// Appel service 1
		// -- récupérer un article existant
		Article updateArticle = articleService.getArticleById("1").data;
		updateArticle.title = "Y'a ECF";
		
		ServiceResponse<Article> response = articleService.saveArticle(updateArticle);
		
		// - 01
		assertEquals(response.code, Constante.CD_SUCCESS);
		
		// - 02
		assertNotNull(response.data);
		
		// Appel service 2
		// -- récupérer article 3
		Article article3 = articleService.getArticleById("3").data;
		
		// -- copier le titre de l'article 3
		updateArticle.title = article3.title;
	
		// -- Mettre à jour
		ServiceResponse<Article> response2 = articleService.saveArticle(updateArticle);
		
		// - 01
		assertEquals(response2.code, Constante.CD_ERR_TCH);
	}
}
