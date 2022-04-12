package tech.klok.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.klok.challenge.configuration.security.JwtUtil;
import tech.klok.challenge.configuration.security.UserDetailsService;
import tech.klok.challenge.dto.auth.AuthenticationResponse;
import tech.klok.challenge.dto.auth.UserDetailsDto;
import tech.klok.challenge.exception.AuthenticationFailedException;

@Service
public class LoginService {
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private JwtUtil jwtUtils;
    
    public AuthenticationResponse login(UserDetailsDto userDetails) throws AuthenticationException {
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();
        
        try {
        	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (AuthenticationException exception) {
			throw new AuthenticationFailedException(username);
		}
        UserDetails register = userService.loadUserByUsername(username);

        String token = jwtUtils.generateToken(register);

        return new AuthenticationResponse(token);
        
    }
}
