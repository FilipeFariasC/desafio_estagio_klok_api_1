package tech.klok.challenge.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
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
	
	private Set<Field> fields;
	
	public Adhesion create(AdhesionPostDto adhesionPostDto, String username) 
			throws ProductNotFoundException, 
				UserNotFoundException, 
				FieldRequiredException, 
				FieldNotExistException {
		
		Adhesion adhesion = mapFromDtoToAdhesion(adhesionPostDto);
		
		User user = userService.findByUsername(username);
		
		Product product = productService.findById(adhesionPostDto.getProductId());
		
		fields = product.getFields();
		
		validateRequiredFields(adhesionPostDto.getReplies());
		
		validateNonExistingFields(adhesionPostDto.getReplies());
		
		convertFieldIdToField(adhesion, adhesionPostDto.getReplies());
		
		adhesion.setProduct(product);
		adhesion.setAquisitionDate(LocalDate.now());
		
		Adhesion created = adhesionRepo.save(adhesion);
		
		user.addAdhesion(created);
		
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
		findById(id);
		
		return this.create(adhesionPostDto, username);
	}
	
	public void deleteById(Long id) throws AdhesionNotFoundException {
		Adhesion adhesion = findById(id);
		
		adhesionRepo.delete(adhesion);
		
//		adhesionRepo.deleteById(id); antes era feito desse jeito porém eu prefiro passar alguma informação com essa exceção
	}
	
	
	private void validateRequiredFields(Set<ReplyPostDto> replies) throws FieldRequiredException {
		Set<Long> ids = replies.stream()
				.map(ReplyPostDto::getFieldId)
				.collect(Collectors.toSet());
		
		for (Field field: fields) {
			if(field.isRequired() && !ids.contains(field.getId())) {
				throw new FieldRequiredException(field);
			}
		}
	}
	
	private void validateNonExistingFields(Set<ReplyPostDto> replies) throws FieldNotExistException {
		Set<Long> ids = fields.stream().map(Field::getId).collect(Collectors.toSet());
		
		for (ReplyPostDto reply: replies) {
			if(!ids.contains(reply.getFieldId())) {
				throw new FieldNotExistException(reply.getFieldId());
			}
		}
	}
	
	private void convertFieldIdToField(Adhesion adhesion, Set<ReplyPostDto> replies) {
		for (ReplyPostDto replyDto : replies) {
			Field field = fieldRepo.findById(replyDto.getFieldId()).get();
			Reply reply = mapFromDtoToReply(replyDto);
			reply.setField(field);
			
			adhesion.addReply(reply);
			
		}
	}
	private Reply mapFromDtoToReply(ReplyPostDto replyDto) {
		Reply reply = new Reply();
		
		reply.setValue(replyDto.getValue());
		
		return reply;
	}
	
	private Adhesion mapFromDtoToAdhesion(AdhesionPostDto dto) {
		Adhesion adhesion = new Adhesion();
		
		adhesion.setChargingDay(dto.getChargingDay());
		adhesion.setNumberOfInstallments(dto.getNumberOfInstallments());
		adhesion.setAmount(dto.getAmount());
		adhesion.setAquisitionDate(LocalDate.now());
		
		
		return adhesion;
	}
}
