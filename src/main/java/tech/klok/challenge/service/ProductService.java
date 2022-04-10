package tech.klok.challenge.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.klok.challenge.dto.ProductDto;
import tech.klok.challenge.dto.post.ProductPostDto;
import tech.klok.challenge.exception.ProductNotFoundException;
import tech.klok.challenge.model.Product;
import tech.klok.challenge.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ModelMapper productMapper;
	
	public Product create(ProductPostDto created){
		Product product = mapToProduct(created);
		
		return productRepo.save(product);
	}
	
	public List<Product> getAll(){
		return productRepo.findAll();
	}
	
	public Product findById(Long id) throws ProductNotFoundException{
		Optional<Product> product = productRepo.findById(id);
		
		if(product.isEmpty())
			throw new ProductNotFoundException(id);
		
		return product.get();
	}
	public Product update(Long id, ProductPostDto productPostDto) throws ProductNotFoundException {
		Optional<Product> product = productRepo.findById(id);
		
		if(product.isEmpty())
			throw new ProductNotFoundException(id);
		
		Product updated = productMapper.map(productPostDto, Product.class);
		updated.setId(product.get().getId());
		
		return productRepo.save(updated);
	}
	
	public void delete(Long id) throws ProductNotFoundException{
		Optional<Product> register = productRepo.findById(id);
		
		if(register.isEmpty())
			throw new ProductNotFoundException(id);
		
		productRepo.delete(register.get());
	}
	

	private Product mapToProduct(ProductPostDto productPostDto) {
		return productMapper.map(productPostDto, Product.class);
	}
	
}
