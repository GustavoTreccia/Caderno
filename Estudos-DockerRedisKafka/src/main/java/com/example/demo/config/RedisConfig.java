package com.example.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuracao do cache com Redis.
 * Define um TTL padrao e TTLs especificos por cache (categories/products),
 * usando JSON (Jackson) para serializar os valores armazenados.
 */
@Configuration
@EnableCaching
public class RedisConfig {

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(5))
				.disableCachingNullValues()
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		cacheConfigurations.put("categories", defaultConfig.entryTtl(Duration.ofMinutes(10)));
		cacheConfigurations.put("products", defaultConfig.entryTtl(Duration.ofMinutes(2)));

		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(defaultConfig)
				.withInitialCacheConfigurations(cacheConfigurations)
				.build();
	}
}
