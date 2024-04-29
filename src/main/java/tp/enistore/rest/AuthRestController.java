package tp.enistore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.bo.Personne;
import tp.enistore.dao.mongo.PersonneMongoRepository;
import tp.enistore.security.JwtService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	PersonneMongoRepository repository;
	
	/**
	 * Point d'entrée pour s'authentifier (générer un token)
	 * @param personne : Le json qu'on envoie {email, password}
	 * @return
	 */
	@PostMapping("/login")
	public String login(@RequestBody Personne personne) {
		
		// Tester la connexion
		// -- récupérer l'user avec l'email envoyé
		Personne user = repository.findByEmail(personne.email);
		
		// Appeler le service JWt pour générer un token
		String token = jwtService.generateToken(user);
		
		return token;
	}
}
