package tech.klok.challenge.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.klok.challenge.dto.UserDto;
import tech.klok.challenge.dto.post.UserPostDto;
import tech.klok.challenge.exception.NonUniqueUsernameException;
import tech.klok.challenge.exception.UserNotFoundException;
import tech.klok.challenge.model.User;
import tech.klok.challenge.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper userMapper;
	
	@PostMapping
	public ResponseEntity<?> create(
		@Valid
		@RequestBody
		UserPostDto userPostDto, 
		HttpServletResponse response) {
		try {
			User created = userService.create(userPostDto);			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{id}")
					.buildAndExpand(created.getId())
					.toUri();
			response.setHeader("Location", uri.toASCIIString());
			
			UserDto dto = mapToUserDto(created);
			
			return ResponseEntity.created(uri).body(dto);
		} catch (NonUniqueUsernameException exception) {
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}
	
	@GetMapping
	public List<UserDto> getAll(){
		return userService.getAll()
			.stream()
			.map(this::mapToUserDto)
			.toList();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		try {
			User user = userService.findById(id);
			UserDto dto = mapToUserDto(user);
			return ResponseEntity.ok(dto);
		} catch (UserNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	@GetMapping("/search")
	public ResponseEntity<?> getByUsername(@RequestParam(name="username", required=true) String username){
		try {
			User user = userService.findByUsername(username);
			UserDto dto = mapToUserDto(user);
			return ResponseEntity.ok(dto);
		} catch (UserNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, 
			@Valid
			@RequestBody
			UserPostDto userPostDto){
		
		try {
			User user = userService.update(id, userPostDto);
			UserDto dto = mapToUserDto(user);
			return ResponseEntity.ok(dto);
		} catch (UserNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id){
		try {
			userService.delete(id);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	
	private UserDto mapToUserDto(User user) {
		return userMapper.map(user, UserDto.class);
	}
}
