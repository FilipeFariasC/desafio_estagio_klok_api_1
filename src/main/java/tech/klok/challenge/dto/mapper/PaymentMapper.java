package tech.klok.challenge.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.klok.challenge.dto.PaymentDto;
import tech.klok.challenge.dto.post.PaymentPostDto;
import tech.klok.challenge.model.Payment;

@Service
public class PaymentMapper {
	@Autowired
	private ModelMapper paymentMapper;
	
	public Payment fromPostDtoToPayment(PaymentPostDto paymentPostDto) {
		return paymentMapper.map(paymentPostDto, Payment.class);
	}
	
	public PaymentDto fromPaymentToDto(Payment payment) {
		return paymentMapper.map(payment, PaymentDto.class);
	}
}
