package com.example.treino.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.treino.models.Role;
import com.example.treino.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.logging.Logger;

@Component
public class JWTUtil {
	
	private String secret = "tokenSecreto";
	
	public String generateToken(User user) {
	    return Jwts.builder()
	        .setSubject(user.getUsername())
	        .claim("roles", user.getRoles().stream()
	            .map(Role::getName)
	            .collect(Collectors.toList()))
	        .setIssuedAt(new Date())
	        .setExpiration(new Date(System.currentTimeMillis() + 864000000))
	        .signWith(SignatureAlgorithm.HS256, secret)
	        .compact();
	}
	
	public Claims extractClaims(String token) {
	    try {
	        return Jwts.parser()
	                .setSigningKey(secret)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    } catch (JwtException e) {
	    	System.err.println("Erro ao processar token JWT: " + e.getMessage());
	        throw new SecurityException("Token JWT inválido ou expirado", e);
	    }
	}

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return (List<String>) extractClaims(token).get("roles");
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, User user) {
        return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }
	
	

}
