package tp.enistore.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import tp.enistore.bo.Personne;


public interface PersonneMongoRepository extends MongoRepository<Personne, String> {

	public Personne findByUid(String uid);
	
	/**
	 * Car spring security doit récupérer un user par son Username
	 * Dans notre cas Username => email
	 * @param email
	 * @return
	 */
	public Personne findByEmail(String email);
}
