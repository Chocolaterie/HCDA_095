package tp.enistore.dao.sql;

import org.springframework.data.repository.CrudRepository;

import tp.enistore.bo.Article;

public interface ArticleSQLRepository extends CrudRepository<Article, Long> {

}
