package tech.klok.challenge.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.klok.challenge.dto.auth.UserDetailsDto;
import tech.klok.challenge.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<?> login(@Valid @RequestBody UserDetailsDto userDetailsDto){
		try {
			return ResponseEntity.ok(loginService.login(userDetailsDto));
		} catch(AuthenticationException exception) {
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}
	
}
