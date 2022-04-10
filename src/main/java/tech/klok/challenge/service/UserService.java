package tech.klok.challenge.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.klok.challenge.dto.UserDto;
import tech.klok.challenge.dto.post.UserPostDto;
import tech.klok.challenge.exception.NonUniqueUsernameException;
import tech.klok.challenge.exception.UserNotFoundException;
import tech.klok.challenge.model.User;
import tech.klok.challenge.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper userMapper;
	
	public User create(UserPostDto created) throws NonUniqueUsernameException{
		Optional<User> user = userRepo.findByUsername(created.getUsername());
		
		if(user.isPresent())
			throw new NonUniqueUsernameException(created.getUsername());

		User newUser = mapToUser(created);
		
		return userRepo.save(newUser);
	}
	
	public List<User> getAll(){
		return userRepo.findAll();
	}
	
	public User findById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepo.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException(id);
		
		return user.get();
	}
	public User findByUsername(String username) throws UserNotFoundException{
		Optional<User> user = userRepo.findByUsername(username);
		
		if(user.isEmpty())
			throw new UserNotFoundException(username);
		
		return user.get();
	}
	public User update(Long id, UserPostDto userPostDto) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException(id);

		User updated = mapToUser(userPostDto);
		updated.setId(id);
		
		return userRepo.save(updated);
	}
	
	public void delete(Long id) throws UserNotFoundException{
		Optional<User> register = userRepo.findById(id);
		
		if(register.isEmpty())
			throw new UserNotFoundException(id);
		
		userRepo.delete(register.get());
	}
	
	private User mapToUser(UserPostDto userPostDto) {
		return userMapper.map(userPostDto, User.class);
	}
	
}
