package tp.enistore.dao;

import java.util.List;

import tp.enistore.bo.Article;

public interface ArticleDAO {

	public List<Article> findAll();
	
	public Article findById(Long id);
	
	public void delete(Long id);
}
