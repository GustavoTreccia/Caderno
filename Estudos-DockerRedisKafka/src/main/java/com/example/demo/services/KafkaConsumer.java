package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consome os eventos publicados em KafkaProducer no topico "product-events".
 * Apenas loga a mensagem recebida, para fins de estudo.
 */
@Component
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = KafkaProducer.TOPIC, groupId = "${spring.kafka.consumer.group-id}")
	public void listen(String message) {
		logger.info("Evento recebido em '{}': {}", KafkaProducer.TOPIC, message);
	}
}
