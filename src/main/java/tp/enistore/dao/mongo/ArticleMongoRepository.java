package tp.enistore.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tp.enistore.bo.Article;

public interface ArticleMongoRepository extends MongoRepository<Article, String>{

	public Article findByUid(String uid);
	
	public Article findByTitle(String title);
	
	public void deleteByUid(String uid);
}
