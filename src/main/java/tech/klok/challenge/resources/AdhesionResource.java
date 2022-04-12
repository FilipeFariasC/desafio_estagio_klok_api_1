package tech.klok.challenge.resources;

import java.security.Principal;

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
import org.springframework.web.bind.annotation.RestController;

import tech.klok.challenge.dto.AdhesionDto;
import tech.klok.challenge.dto.post.AdhesionPostDto;
import tech.klok.challenge.exception.AdhesionNotFoundException;
import tech.klok.challenge.exception.FieldNotExistException;
import tech.klok.challenge.exception.FieldRequiredException;
import tech.klok.challenge.exception.ProductNotFoundException;
import tech.klok.challenge.exception.UserNotFoundException;
import tech.klok.challenge.model.Adhesion;
import tech.klok.challenge.service.AdhesionService;

@RestController
@RequestMapping("/adhesions")
public class AdhesionResource {
	
	@Autowired
	private ModelMapper adhesionMapper;
	
	@Autowired
	private AdhesionService adhesionService;
	
	@PostMapping
	public ResponseEntity<?> create(
			@Valid
			@RequestBody AdhesionPostDto adhesionPostDto, Principal principal){
		try {
			Adhesion adhesion = adhesionService.create(adhesionPostDto, principal.getName());
			
			AdhesionDto dto = mapToDto(adhesion);
			
			return ResponseEntity.ok(dto);
		} catch (ProductNotFoundException | UserNotFoundException | FieldRequiredException | FieldNotExistException exception) {
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(adhesionService.getAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getAll(@PathVariable("id") Long id){
		try {
			Adhesion adhesion = adhesionService.findById(id);
			
			AdhesionDto dto = mapToDto(adhesion);
			
			return ResponseEntity.ok(dto);
		} catch (AdhesionNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable("id") Long id,
			@Valid
			@RequestBody AdhesionPostDto adhesionPostDto,
			Principal principal){
		try {
			Adhesion adhesion = adhesionService.update(id, adhesionPostDto, principal.getName());
			
			AdhesionDto dto = mapToDto(adhesion);
			
			return ResponseEntity.ok(dto);
		} catch (AdhesionNotFoundException | ProductNotFoundException | UserNotFoundException | FieldRequiredException
				| FieldNotExistException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		try {
			adhesionService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (AdhesionNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	private AdhesionDto mapToDto(Adhesion adhesion) {
		return adhesionMapper.map(adhesion, AdhesionDto.class);
	}
}
