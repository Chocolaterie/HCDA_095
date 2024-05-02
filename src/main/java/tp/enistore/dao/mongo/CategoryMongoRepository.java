package tp.enistore.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tp.enistore.bo.Article;
import tp.enistore.bo.Category;

public interface CategoryMongoRepository extends MongoRepository<Category, String>{

	public Category findByName(String name);
}
