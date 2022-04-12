package tech.klok.challenge.resources;

import java.util.List;
import java.util.stream.Collectors;

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

import tech.klok.challenge.dto.FieldDto;
import tech.klok.challenge.dto.ProductDto;
import tech.klok.challenge.dto.post.ProductPostDto;
import tech.klok.challenge.exception.ProductNotFoundException;
import tech.klok.challenge.model.Product;
import tech.klok.challenge.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper productMapper;
	
	@PostMapping
	public ResponseEntity<?> create(
			@Valid
			@RequestBody ProductPostDto productPostDto){
		Product product = productService.create(productPostDto);
		 
		return ResponseEntity.ok(mapToDto(product));
	}
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<ProductDto> dtos = productService.getAll()
				.stream()
				.map(this::mapToDto)
				.toList();
		
		return ResponseEntity.ok(dtos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		try {
			Product product = productService.findById(id);
			
			ProductDto dto = mapToDto(product);
			
			return ResponseEntity.ok(dto);
		} catch (ProductNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id,
			@Valid
			@RequestBody ProductPostDto productPostDto){
		
		try {
			Product product = productService.update(id, productPostDto);
			
			ProductDto dto = mapToDto(product);
			
			return ResponseEntity.ok(dto);
		} catch (ProductNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id){
		try {
			productService.delete(id);
			
			return ResponseEntity.ok().build();
		} catch (ProductNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
	
	private ProductDto mapToDto(Product product) {
		ProductDto dto = productMapper.map(product, ProductDto.class);
		
		dto.setFields(product.getFields()
			.stream()
			.map((field)-> productMapper.map(field, FieldDto.class))
			.collect(Collectors.toSet()));
		
		return dto;
	}
	
}
