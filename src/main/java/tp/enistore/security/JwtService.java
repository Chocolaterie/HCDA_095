package tp.enistore.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import tp.enistore.bo.Personne;

@Service
public class JwtService {

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	
	/**
	 * Permet de récupérer la clé secrete JWT de notre app
	 * @return
	 */
	public Key getSecretKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	/**
	 * Va générer un token
	 * @return
	 */
	public String generateToken(Personne personne) {
		// -- identifiant unique métier d'un user (id du token)
		String email = personne.email;
		
		// -- temps de vie du token
		Date tokenLifetime = new Date(System.currentTimeMillis() + 1000 * 60 * 24);
		
		// la liste des clé valeur à crypter
		Map<String, Object> claims = new HashMap<String, Object>();
		
		// Appeler la lib jwt pour gener un token sur une donnée
		String token = Jwts.builder().setClaims(claims).setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(tokenLifetime)
				.signWith(getSecretKey(), SignatureAlgorithm.HS256)
				.compact();
		
		// Retourner le fameux token généré
		return token;
	}
}
