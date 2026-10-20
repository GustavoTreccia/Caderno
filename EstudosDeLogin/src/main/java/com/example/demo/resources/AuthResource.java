package com.dev.jwtstudy.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ConfirmationToken;
import com.example.demo.entities.User;
import com.example.demo.repositories.ConfirmationTokenRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private ConfirmationTokenRepository tokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * Ao clicar no link do e-mail, irá chamar o endpoint auth/confirm?token=...
	 * Irá buscar o token no banco de dados e verificar se é válido e não expirou
	 * Se tudo estiver ok, ativa o usuário e deleta o token
	 * */
	
	@GetMapping("/confirm")
	public ResponseEntity<String> confirm(@RequestParam String token){
		
		ConfirmationToken confirmation = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token inválido"));
		
		if (confirmation.getExpiresAt().isBefore(LocalDateTime.now())){
			return ResponseEntity.badRequest().body("Token Expirado");
		}
		
		User user = confirmation.getUser();
		user.setEnabled(true);
		
		userRepository.save(user);
		tokenRepository.delete(confirmation);
		
		return ResponseEntity.ok("Conta confirmada com sucesso");
		
	}

}
