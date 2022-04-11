package tech.klok.challenge.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import tech.klok.challenge.dto.mapper.AdhesionMapper;
import tech.klok.challenge.dto.post.AdhesionPostDto;
import tech.klok.challenge.dto.post.ReplyPostDto;
import tech.klok.challenge.exception.AdhesionNotFoundException;
import tech.klok.challenge.exception.FieldNotExistException;
import tech.klok.challenge.exception.FieldRequiredException;
import tech.klok.challenge.exception.ProductNotFoundException;
import tech.klok.challenge.exception.UserNotFoundException;
import tech.klok.challenge.model.Adhesion;
import tech.klok.challenge.model.Field;
import tech.klok.challenge.model.Product;
import tech.klok.challenge.model.Reply;
import tech.klok.challenge.model.User;
import tech.klok.challenge.repository.AdhesionRepository;
import tech.klok.challenge.repository.FieldRepository;
import tech.klok.challenge.repository.UserRepository;


public class AdhesionService {
	@Autowired
	private AdhesionRepository adhesionRepo;
	@Autowired
	private AdhesionMapper adhesionMapper;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FieldRepository fieldRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public Adhesion create(AdhesionPostDto adhesionPostDto, String username) 
			throws ProductNotFoundException, 
				UserNotFoundException, 
				FieldRequiredException, 
				FieldNotExistException {
		Adhesion adhesion = adhesionMapper.fromPostDtoToAdhesion(adhesionPostDto);
		User user = userService.findByUsername(username);
		Product product = productService.findById(adhesionPostDto.getProductId());
		
		
		Set<Field> fields = validateRequiredFields(adhesionPostDto.getProductId(), adhesionPostDto.getReplies());
		validateNonExistingFields(fields, adhesionPostDto.getReplies());
		
		convertFieldIdToField(adhesion, adhesionPostDto.getReplies());
		
		adhesion.setProduct(product);
		adhesion.setAquisitionDate(LocalDate.now());
		user.getAdhesions().add(adhesion);
		userRepo.save(user);
		
		return adhesion;
	}
	
	public List<Adhesion> getAll() {
		return adhesionRepo.findAll();
	}
	
	public Adhesion findById(Long id) throws AdhesionNotFoundException {
		Optional<Adhesion> adhesion = adhesionRepo.findById(id);
		
		if (adhesion.isEmpty())
			throw new AdhesionNotFoundException(id);
		
		return adhesion.get();
	}
	public Adhesion update(Long id, AdhesionPostDto adhesionPostDto, String username) throws AdhesionNotFoundException, ProductNotFoundException, UserNotFoundException, FieldRequiredException, FieldNotExistException {
		Adhesion adhesion = findById(id);
		
		return this.create(adhesionPostDto, username);
	}
	
	public void deleteById(Long id) throws AdhesionNotFoundException {
		Adhesion adhesion = findById(id);
		
		adhesionRepo.delete(adhesion);
		
//		adhesionRepo.deleteById(id); antes era feito desse jeito porém eu prefiro passar alguma informação com essa exceção
	}
	
	
	private Set<Field> validateRequiredFields(Long productId, Set<ReplyPostDto> replies) throws FieldRequiredException {
		Set<Field> fields = fieldRepo.findFieldsByProductId(productId);
		
		Set<Long> ids = replies.stream()
				.map(ReplyPostDto::getFieldId)
				.collect(Collectors.toSet());
		
		for (Field field: fields) {
			if(field.isRequired() && !ids.contains(field.getId())) {
				throw new FieldRequiredException(field);
			}
		}
		return fields;
	}
	
	private void validateNonExistingFields(Set<Field> fields, Set<ReplyPostDto> replies) throws FieldNotExistException {
		Set<Long> ids = fields.stream().map(Field::getId).collect(Collectors.toSet());
		
		for (ReplyPostDto reply: replies) {
			if(!ids.contains(reply.getFieldId())) {
				throw new FieldNotExistException(reply.getFieldId());
			}
		}
	}
	
	private void convertFieldIdToField(Adhesion adhesion, Set<ReplyPostDto> replies) {
		replies.stream()
			.forEach((replyDto)->{
				Reply reply = mapper.map(replyDto, Reply.class);
				Field field = fieldRepo.findById(replyDto.getFieldId()).get();
				
				reply.setField(field);
				adhesion.getReplies().add(reply);
				
			}
					
					);
	}
}
