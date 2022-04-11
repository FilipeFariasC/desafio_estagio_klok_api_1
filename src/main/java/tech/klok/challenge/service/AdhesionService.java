package tech.klok.challenge.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import tech.klok.challenge.dto.mapper.AdhesionMapper;
import tech.klok.challenge.dto.post.AdhesionPostDto;
import tech.klok.challenge.dto.post.ReplyPostDto;
import tech.klok.challenge.exception.FieldRequiredException;
import tech.klok.challenge.exception.ProductNotFoundException;
import tech.klok.challenge.exception.UserNotFoundException;
import tech.klok.challenge.model.Adhesion;
import tech.klok.challenge.model.Field;
import tech.klok.challenge.model.User;
import tech.klok.challenge.repository.AdhesionRepository;
import tech.klok.challenge.repository.FieldRepository;


public class AdhesionService {
	@Autowired
	private AdhesionRepository adhesionRepo;
	@Autowired
	private AdhesionMapper adhesionMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FieldRepository fieldRepository;
	
	public Adhesion create(AdhesionPostDto adhesionPostDto, String username) 
			throws ProductNotFoundException, UserNotFoundException, FieldRequiredException {
		User user = userService.findByUsername(username);
		
		Adhesion adhesion = adhesionMapper.fromPostDtoToAdhesion(adhesionPostDto);
		
		validateRequiredFields(adhesionPostDto.getProductId(), adhesionPostDto.getReplies());
		
		Adhesion created = adhesionRepo.save(adhesion);
		
		return created;
	}

	private void validateRequiredFields(Long productId, Set<ReplyPostDto> replies) throws FieldRequiredException {
		Set<Field> fields = fieldRepository.findFieldsByProductId(productId);
		
		Set<Long> ids = replies.stream()
				.map(ReplyPostDto::getFieldId)
				.collect(Collectors.toSet());
		
		for (Field field: fields) {
			if(field.isRequired() && !ids.contains(field.getId())) {
				throw new FieldRequiredException(field);
			}
		}
	}
}
