package tp.enistore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tp.enistore.bo.Article;
import tp.enistore.bo.ServiceResponse;
import tp.enistore.service.ArticleService;

@SpringBootTest
@ActiveProfiles(value = {"mock"})
public class ArticleServiceTests {

	@Autowired
	ArticleService articleService;
	
	@Test
	public void test01() {
		
	}
}
