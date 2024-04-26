package tp.enistore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tp.enistore.dao.mongo.PersonneMongoRepository;

@Service
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	PersonneMongoRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByEmail(username);
		return user;
	}

}
