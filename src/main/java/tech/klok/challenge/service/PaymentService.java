package tech.klok.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import tech.klok.challenge.dto.mapper.PaymentMapper;
import tech.klok.challenge.dto.post.PaymentPostDto;
import tech.klok.challenge.model.Payment;

@Service
public class PaymentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public static final String url = "http://localhost:8081/api/payments/";
	
	public Payment create(PaymentPostDto paymentDto) throws RestClientException{
		return restTemplate.postForObject(url, paymentDto, Payment.class);
	}
	
	public List<Payment> getAll() throws RestClientException{
		return restTemplate.getForObject(url, List.class);
	}
	public Payment getById(Long id) throws RestClientException{
		return restTemplate.getForObject(url + id, Payment.class);
	}
	public void deleteById(Long id) throws RestClientException{
		restTemplate.delete(url + id);
	}
}
