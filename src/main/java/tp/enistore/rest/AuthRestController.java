package tp.enistore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.bo.Personne;
import tp.enistore.bo.ServiceResponse;
import tp.enistore.dao.mongo.PersonneMongoRepository;
import tp.enistore.security.JwtService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
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
	public ServiceResponse<String> login(@RequestBody Personne personne) {
		ServiceResponse<String> response = new ServiceResponse<String>();
		
		// Tester la connexion
		// -- récupérer l'user avec l'email envoyé
		Personne user = repository.findByEmail(personne.email);
		
		// Appeler le service JWt pour générer un token
		String token = jwtService.generateToken(user);
		
		// erreur 701
		if (token == null || token.isEmpty()) {
			response.code = "701";
			response.message = "Couple email mot de passe incorrect";
			
			return response;
		}
		
		response.code = "200";
		response.message = "Connecté(e) avec succès";
		response.data = token;
		
		return response;
	}
}
