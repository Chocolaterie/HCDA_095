package tp.enistore.dao.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.enistore.bo.Article;
import tp.enistore.bo.Category;
import tp.enistore.dao.ArticleDAO;

@Component
@Profile("mock")
public class ArticleDAOMock implements ArticleDAO {

	private List<Article> DB_ARTICLES;
	
	public ArticleDAOMock() {
		DB_ARTICLES = new ArrayList<Article>();
		
		// -- category
		Category category = new Category("1", "Informatique");
		
		// -- Articles
		DB_ARTICLES.add(new Article("1", "Beurre Salé", category));
		
		DB_ARTICLES.add(new Article("2", "Crevette Nutella", category));
		
		DB_ARTICLES.add(new Article("3", "Cassoulet Oeuf", category));
	}
	
	
	@Override
	public List<Article> findAll() {
		return DB_ARTICLES;
	}

	@Override
	public Article findByUid(String uid) {
		// Predicate (tout langage PO important)
		Optional<Article> foundArticle = DB_ARTICLES.stream().filter(article -> article.uid.equals(uid)).findFirst();
		
		if (foundArticle.isPresent()) {
			return foundArticle.get();
		}
		
		return null;
	}

	@Override
	public Article findByTitle(String title) {
		// Predicate (tout langage PO important)
		Optional<Article> foundArticle = DB_ARTICLES.stream().filter(article -> article.title.equals(title)).findFirst();
		
		if (foundArticle.isPresent()) {
			return foundArticle.get();
		}
		
		return null;
	}
	
	@Override
	public Article findByTitleExcludeUid(String title, String excludeUid) {
		// Predicate (tout langage PO important)
		Optional<Article> foundArticle = DB_ARTICLES.stream()
				.filter(article -> article.title.equals(title) && !article.uid.equals(excludeUid))
				.findFirst();
		
		if (foundArticle.isPresent()) {
			return foundArticle.get();
		}
		
		return null;
	}

	@Override
	public void delete(String uid) {
		// Predicate import de java.util.functions
		// -- créer une condition lambda (un predicate)
		Predicate<Article> predicate = article -> article.uid.equals(uid);
		
		// -- supprimer tout les articles qui correspondent à la condition
		DB_ARTICLES.removeIf(predicate);
	}

	@Override
	public Article save(Article article) {
		// Creation
		if (article.uid == null || article.uid.isEmpty()) {
			// générer un faux uid
			article.uid = UUID.randomUUID().toString();
			
			DB_ARTICLES.add(article);
			
			return article;
		}
		// si update
		Article foundArticle = findByUid(article.uid);
		
		// remplacer title
		foundArticle.title = article.title;
		
		return article;
	}

}
