package tp.enistore.dao;

import java.util.List;

import tp.enistore.bo.Article;

public interface ArticleDAO {

	public List<Article> findAll();
	
	public Article findByUid(String uid);
	
	public void delete(String uid);
}
