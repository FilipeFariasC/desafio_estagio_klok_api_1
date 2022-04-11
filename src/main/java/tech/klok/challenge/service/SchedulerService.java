package tech.klok.challenge.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tech.klok.challenge.dto.post.ChargePostDto;
import tech.klok.challenge.model.Adhesion;
import tech.klok.challenge.model.categories.ChargeStatus;
import tech.klok.challenge.repository.AdhesionRepository;

import static tech.klok.challenge.configuration.RabbitMQConfiguration.*;

@Service
public class SchedulerService {
	
	@Autowired
	private AdhesionRepository adhesionRepo;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/*
	 * a expressão cron abaixo 
	 */
	@Scheduled(cron="0 0 4 * * *", zone="America/Recife")
	private void charge() {
		List<Adhesion> adhesions = adhesionRepo.findAll();
		
		Integer dayOfMonth = LocalDate.now().getDayOfMonth();
		
		for(Adhesion adhesion : adhesions) {
			if(adhesion.getChargingDay() == dayOfMonth) {
				ChargePostDto chargePostDto = new ChargePostDto();
				
				chargePostDto.setAmount(adhesion.getAmount());
				chargePostDto.setAdhesionId(adhesion.getId());
				chargePostDto.setChargingDate(adhesion.getAquisitionDate());
				chargePostDto.setStatus(ChargeStatus.PENDING);
				
				rabbitTemplate.convertAndSend(
						CHARGE_EXCHANGE,
						ROUTING_KEY,
						chargePostDto
						);
			}
		}
	} 
}
