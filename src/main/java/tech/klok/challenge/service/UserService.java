package tech.klok.challenge.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.klok.challenge.dto.UserDto;
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
	
	public UserDto save(User user) throws NonUniqueUsernameException{
		Optional<User> register = userRepo.findByUsername(user.getUsername());
		
		if(register.isPresent())
			throw new NonUniqueUsernameException(user.getUsername());
		
		return mapToUserDto(userRepo.save(user));
	}
	
	public List<User> getAll(){
		return userRepo.findAll();
	}
	
	public UserDto findById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepo.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException(id);
		
		return mapToUserDto(user.get());
	}
	public UserDto findByUsername(String username) throws UserNotFoundException{
		Optional<User> user = userRepo.findByUsername(username);
		
		if(user.isEmpty())
			throw new UserNotFoundException(username);
		
		return mapToUserDto(user.get());
	}
	public UserDto update(Long id, UserDto userDto) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException(id);
		
		User updated = userMapper.map(userDto, User.class);
		updated.setId(user.get().getId());
		
		return mapToUserDto(userRepo.save(updated));
	}
	
	public void delete(Long id) throws UserNotFoundException{
		Optional<User> register = userRepo.findById(id);
		
		if(register.isEmpty())
			throw new UserNotFoundException(id);
		
		
		userRepo.delete(register.get());
	}
	
	private UserDto mapToUserDto(User user) {
		return userMapper.map(user, UserDto.class);
	}
}
