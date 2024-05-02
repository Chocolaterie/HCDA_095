package tp.enistore.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.enistore.bo.Category;
import tp.enistore.dao.CategoryDAO;

@Component
@Profile("mongo")
public class CategoryDAOMongo implements CategoryDAO {

	@Autowired
	CategoryMongoRepository repository;

	@Override
	public List<Category> findAll() {
		return repository.findAll();
	}


	@Override
	public Category save(Category category) {
		// UPDATE :::::::::::
		// Si existe alors update
		if (category.id != null && !"".equals(category.id)) {

			// On va essayer de récupérer la personne en question de la bdd
			Category foundCategory = repository.findById(category.id).get();

			// Si l'article a été trouvé
			if (foundCategory != null) {
				// Solution 1 : Mettre le json en base
				// Envoyer l'id mongo interne dans l'article JSON
				category.id = foundCategory.id;
				
				return repository.save(category);
			}
		}
		// Sinon creation
		else {
			// save en base
			return repository.save(category);
		}
		
		return null;
	}

	@Override
	public Category findByName(String name) {
		return repository.findByName(name);
	}

}
