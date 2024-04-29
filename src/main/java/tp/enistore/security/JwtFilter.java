package tp.enistore.security;

import java.io.IOException;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.enistore.dao.mongo.PersonneMongoRepository;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	PersonneMongoRepository repository;
	
	@Autowired
	JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Récupérer header authorization
		String authHeader = request.getHeader("Authorization");
		
		// Verifier qu'on est en Bearer (sinon stop erreur)
		// Si aucun entête et pas bearer alors Erreur
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// Récupérer le bearer token
		// Substring de 7 car
		// ex : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdW5kaUBnbWFpbC5jb20iLCJpYXQiOjE3MTQzODI3NzEsImV4cCI6MTcxNDM4NDIxMX0.8sw3ASoCigMYlcW5eYkeq1qjUwBxxWKIPJEcXfojESU
		// Extraire à partir l'espace donc "Bearer "
		String jwt = authHeader.substring(7);
		
		// Tester la validité d'un token (l'expiration)
		// -- récupérer les clé/valeur du token
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(jwtService.getSecretKey()).build()
				.parseClaimsJws(jwt)
				.getBody();
		
		// -- récupérer la date d'expiration
		Function<Claims, Date> expirationFunction = Claims::getExpiration;
		Date expirationData = expirationFunction.apply(claims);
				
		// -- récupérer l'email
		String email = claims.getSubject();
		
		// -- tester que le date ne dépasse le temps actuel
		// -- si la date d'expiration est inférieur à tout de suite
		// -- true : donc invalide
		if (expirationData.before(new Date())){
			System.out.println("Erreur token");
			return;
		}
		
		// Finir la validation du token
		// Tester que c'est la première fois qu'on va valider un user spring security
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			// Instancier un user details
			UserDetails usersDetails = repository.findByEmail(email);
			
			// Transferer l'user dans le auth token (car on remplace basic auth)
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null, usersDetails.getAuthorities());
			authToken.setDetails(usersDetails);
			
			// Mettre à jour le contexte de sécurité
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		
		// par défaut
		filterChain.doFilter(request, response);
	}

}
