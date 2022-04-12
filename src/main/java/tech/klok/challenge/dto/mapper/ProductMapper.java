package tech.klok.challenge.dto.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.klok.challenge.dto.FieldDto;
import tech.klok.challenge.dto.ProductDto;
import tech.klok.challenge.dto.post.FieldPostDto;
import tech.klok.challenge.dto.post.ProductPostDto;
import tech.klok.challenge.model.Field;
import tech.klok.challenge.model.Product;
import tech.klok.challenge.service.ProductService;

@Service
public class ProductMapper {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductService productService;
	
	public Product fromPostDtoToProduct(ProductPostDto productPostDto) {
		Product product = modelMapper.map(productPostDto, Product.class);
		product.setFields(productPostDto
				.getFields()
				.stream()
				.map(this::fromPostDtoToField)
				.collect(Collectors.toSet()));
		
		return product;
	}
	public Product fromDtoToProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product.setFields(productDto
				.getFields()
				.stream()
				.map(this::fromDtoToField)
				.collect(Collectors.toSet()));
		
		return product;
	}
	public ProductDto fromProductToDto(Product product) {
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		
		productDto.setFields(product
				.getFields()
				.stream()
				.map(this::fromFieldToFieldDto)
				.collect(Collectors.toSet()));
		
		return productDto;
	}
	
	private FieldDto fromFieldToFieldDto(Field field) {
		return modelMapper.map(field, FieldDto.class);
	}
	private Field fromPostDtoToField(FieldPostDto fieldDto) {
		return modelMapper.map(fieldDto, Field.class);
	}
	private Field fromDtoToField(FieldDto fieldDto) {
		return modelMapper.map(fieldDto, Field.class);
	}
}
