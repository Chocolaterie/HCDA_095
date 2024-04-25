package tp.enistore.dao.mongo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.enistore.bo.Article;
import tp.enistore.dao.ArticleDAO;

@Component
@Profile("mongo")
public class ArticleDAOMongo implements ArticleDAO {

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

	@Override
	public void save(Article article) {
		// UPDATE :::::::::::
		// Si existe alors update
		if (article.uid != null && !"".equals(article.uid)) {

			// On va essayer de récupérer la personne en question de la bdd
			Article foundArticle = repository.findByUid(article.uid);

			// Si l'article a été trouvé
			if (foundArticle != null) {
				// Solution 1 : Mettre le json en base
				// Envoyer l'id mongo interne dans l'article JSON
				article.id = foundArticle.id;
				article.uid = foundArticle.uid;
				
				repository.save(article);
			}
		}
		// Sinon creation
		else {
			// générer l'uid métier
			article.uid = UUID.randomUUID().toString();

			// save en base
			repository.save(article);
		}
	}

}
