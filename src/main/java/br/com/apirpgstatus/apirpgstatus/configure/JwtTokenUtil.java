package br.com.apirpgstatus.apirpgstatus.configure;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.apirpgstatus.apirpgstatus.model.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	
	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String getIdFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	//retorna expiration date do token jwt 
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//para retornar qualquer informação do token nos iremos precisar da secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//gera token para user
	public String generateToken(Usuarios usuarios) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, usuarios.getId().toString());
	}
	
	//Cria o token e devine tempo de expiração pra ele
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	//valida o token
	public Boolean validateToken(String token, Usuarios usuario) {
		final Long idValidate = Long.valueOf(getIdFromToken(token));
		return (idValidate == usuario.getId()) && !isTokenExpired(token);
	}

}