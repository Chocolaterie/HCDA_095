package tp.enistore.dao.mock;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.enistore.bo.Category;
import tp.enistore.dao.CategoryDAO;

@Component
@Profile("mock")
public class CategoryDAOMock implements CategoryDAO {

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

}
