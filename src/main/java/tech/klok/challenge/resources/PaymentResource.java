package tech.klok.challenge.resources;

import java.net.ConnectException;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import tech.klok.challenge.dto.PaymentDto;
import tech.klok.challenge.dto.post.PaymentPostDto;
import tech.klok.challenge.model.Payment;
import tech.klok.challenge.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentResource {
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ModelMapper paymentMapper;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PaymentPostDto paymentPostDto){
		try {
			Payment payment = paymentService.create(paymentPostDto);
			
			PaymentDto dto = mapToDto(payment);
			
			return ResponseEntity.ok(dto);
		} catch(ResourceAccessException exception) {
			return ResponseEntity.badRequest().body(exception.getMessage());
		} catch(RestClientResponseException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado uma cobrança com esse identificador");
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		try {
			List<PaymentDto> dtos = paymentService.getAll()
					.stream()
					.map(this::mapToDto)
					.toList();
	
			return ResponseEntity.ok(dtos);
		} catch(ResourceAccessException  exception) {
			return ResponseEntity.badRequest().body("Não foi possível se conectar ao servidor");
		} 
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		try {
			Payment payment = paymentService.getById(id);
			
			PaymentDto dto = mapToDto(payment);
			
			return ResponseEntity.ok(dto);
		} catch(ResourceAccessException exception) {
			return ResponseEntity.badRequest().body("Não foi possível se conectar ao servidor");
		} catch(RestClientResponseException exception) {
			return ResponseEntity.badRequest().body("Não foi encontrado uma cobrança com esse identificador");
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		try {
			paymentService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch(ResourceAccessException exception) {
			return ResponseEntity.badRequest().body("Não foi possível se conectar ao servidor");
		} catch(RestClientResponseException exception) {
			return ResponseEntity.badRequest().body("Não foi encontrado uma cobrança com esse identificador");
		}
	}
	
	private PaymentDto mapToDto(Payment payment) {
		return paymentMapper.map(payment, PaymentDto.class);
	}
}
