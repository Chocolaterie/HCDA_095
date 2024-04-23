package tp.enistore.dao.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.enistore.bo.Article;
import tp.enistore.dao.ArticleDAO;

@Component
@Profile("sql")
public class ArticleDAOSQL implements ArticleDAO {

	@Autowired
	ArticleSQLRepository repository;
	
	@Override
	public List<Article> findAll() {
		return (List<Article>) repository.findAll();
	}

	@Override
	public Article findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
