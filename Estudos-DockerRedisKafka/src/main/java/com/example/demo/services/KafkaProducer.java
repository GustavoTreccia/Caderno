package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Publica eventos de dominio do Product no topico Kafka "product-events".
 * Usado para estudo de producer/consumer com Spring Kafka.
 */
@Service
public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	public static final String TOPIC = "product-events";

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	public void sendProductEvent(String eventType, ProductDTO product) {
		try {
			String payload = objectMapper.writeValueAsString(new ProductEvent(eventType, product));
			kafkaTemplate.send(TOPIC, String.valueOf(product.id()), payload);
		} catch (JsonProcessingException e) {
			logger.error("Falha ao serializar evento de produto", e);
		}
	}

	public void send(String message) {
		kafkaTemplate.send(TOPIC, message);
	}

	public record ProductEvent(String eventType, ProductDTO product) {
	}
}
