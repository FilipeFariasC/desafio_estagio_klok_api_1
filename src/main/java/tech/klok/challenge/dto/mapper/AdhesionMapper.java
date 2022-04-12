package tech.klok.challenge.dto.mapper;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.klok.challenge.dto.AdhesionDto;
import tech.klok.challenge.dto.post.AdhesionPostDto;
import tech.klok.challenge.exception.ProductNotFoundException;
import tech.klok.challenge.model.Adhesion;
import tech.klok.challenge.model.Product;
import tech.klok.challenge.service.ProductService;

@Service
public class AdhesionMapper {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;
	
	public AdhesionDto fromAdhesionToDto(Adhesion adhesion) {
		AdhesionDto adhesionDto = modelMapper.map(adhesion, AdhesionDto.class);
		
		adhesionDto.setProductId(adhesion.getProduct().getId());
		
		
		return adhesionDto;
	}
	
	public Adhesion fromPostDtoToAdhesion(AdhesionPostDto adhesionPostDto) throws ProductNotFoundException {
		Product product = productService.findById(adhesionPostDto.getProductId());
		
		Adhesion adhesion = modelMapper.map(adhesionPostDto, Adhesion.class);
		
		adhesion.setProduct(product);
		return adhesion;
	}


	public Adhesion fromDtoToAdhesion(AdhesionPostDto adhesionPostDto) throws ProductNotFoundException {
		Adhesion adhesion = new Adhesion();
		
		adhesion.setAmount(adhesionPostDto.getAmount());
		adhesion.setAquisitionDate(LocalDate.now());
		adhesion.setChargingDay(adhesionPostDto.getChargingDay());
		Product product = productService.findById(adhesionPostDto.getProductId());
		adhesion.setProduct(product);
		
		return adhesion;
	}
	
}
