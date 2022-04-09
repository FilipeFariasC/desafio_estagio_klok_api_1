package tech.klok.challenge.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Classe de configuracao do RabbitMQ
 * @author filipefariasc
 *
 */
@Configuration
public class RabbitMQConfiguration {
	// nome da fila da cobrança
	public static final String CHARGE_QUEUE = "charge_queue";
	public static final String CHARGE_EXCHANGE = "charge_exchange";
	public static final String ROUTING_KEY = "charge_routing_key";
	
	@Bean
	public Queue queue() {
		return new Queue(CHARGE_QUEUE);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(CHARGE_EXCHANGE);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder
				.bind(queue)
				.to(exchange)
				.with(ROUTING_KEY);
	}
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
		RabbitTemplate template = new RabbitTemplate(factory);
		template.setMessageConverter(messageConverter());
		
		return template;
	}
	
}
