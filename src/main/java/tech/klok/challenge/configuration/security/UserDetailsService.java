package tech.klok.challenge.configuration.security;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tech.klok.challenge.model.User;
import tech.klok.challenge.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> found = userRepo.findByUsername(username);
		
		if(found.isEmpty())
			return null;
		
		User user = found.get();
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new HashSet<SimpleGrantedAuthority>());
	}

}
