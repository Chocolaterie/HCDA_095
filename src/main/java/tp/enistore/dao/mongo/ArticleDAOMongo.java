package tp.enistore.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.enistore.bo.Article;
import tp.enistore.dao.ArticleDAO;

@Component
@Profile("mongo")
public class ArticleDAOMongo implements ArticleDAO{

	@Autowired
	ArticleMongoRepository repository;
	
	@Override
	public List<Article> findAll() {
		return repository.findAll();
	}

	@Override
	public Article findByUid(String uid) {
		return repository.findByUid(uid);
	}

	@Override
	public void delete(String uid) {
		repository.deleteByUid(uid);
	}

}
