package tech.klok.challenge.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import tech.klok.challenge.model.Charge;
import tech.klok.challenge.service.ChargeService;

@RestController
@RequestMapping("/charges")
public class ChargeResource {
	@Autowired
	private ChargeService chargeService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
//		return ResponseEntity.ok(charges);
		try {
			List<Charge> charges = chargeService.getAll();
			
			return ResponseEntity.ok(charges);
		}catch(RestClientException exception) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		try {
			List<Charge> charges = chargeService.findAllById(id);
			
			return ResponseEntity.ok(charges);
		}catch(RestClientException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
