package tech.klok.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import tech.klok.challenge.model.Charge;

@Service
public class ChargeService {
	@Autowired
	private RestTemplate restTemplate;
	
	private final String url = "http://localhost:8081/api/charges/";
	
	public List<Charge> getAll() throws RestClientException {
		return restTemplate.getForObject(url, List.class);
	}
	public List<Charge> findAllById(Long id) throws RestClientException{
		return restTemplate.getForObject(url + id, List.class);
	}
	
}
