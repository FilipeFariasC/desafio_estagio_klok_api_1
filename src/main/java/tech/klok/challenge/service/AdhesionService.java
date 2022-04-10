package tech.klok.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;

import tech.klok.challenge.dto.mapper.AdhesionMapper;
import tech.klok.challenge.dto.post.AdhesionPostDto;
import tech.klok.challenge.exception.ProductNotFoundException;
import tech.klok.challenge.exception.UserNotFoundException;
import tech.klok.challenge.model.Adhesion;
import tech.klok.challenge.model.User;
import tech.klok.challenge.repository.AdhesionRepository;


public class AdhesionService {
	@Autowired
	private AdhesionRepository adhesionRepo;
	@Autowired
	private AdhesionMapper adhesionMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	public Adhesion create(AdhesionPostDto adhesionPostDto, String username) 
			throws ProductNotFoundException, UserNotFoundException {
		User user = userService.findByUsername(username);
		
		Adhesion adhesion = adhesionMapper.fromPostDtoToAdhesion(adhesionPostDto);
		
		Adhesion created = adhesionRepo.save(adhesion);
		
		return created;
	}
//	public AdhesionDto
}
