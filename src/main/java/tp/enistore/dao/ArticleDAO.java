package tp.enistore.dao;

import java.util.List;

import tp.enistore.bo.Article;

public interface ArticleDAO {

	public List<Article> findAll();
	
	public Article findByUid(String uid);
	
	public Article findByTitle(String title);
	
	public Article findByTitleExcludeUid(String title, String exlcudeUid);
	
	public void delete(String uid);
	
	public Article save(Article article);
}
