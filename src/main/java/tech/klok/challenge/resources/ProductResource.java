package tech.klok.challenge.resources;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.klok.challenge.dto.ProductDto;
import tech.klok.challenge.model.Product;
import tech.klok.challenge.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ModelMapper productMapper;
	
	private ProductDto mapToProductDto(Product product) {
		return productMapper.map(product, ProductDto.class);
	}
}
