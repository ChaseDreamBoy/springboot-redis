package com.sun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Change the RedisTemplate serialization mode.
 * 
 * @author dreamboy
 *
 */
@Configuration
public class RedisConfig {

	/**
	 * 
	 * redisTemplate default serialization mode is jdkSerializeable.
	 * 
	 * StringRedisTemplate default serialization mode is StringRedisSerializer.
	 * 
	 * Change the RedisTemplate serialization mode.
	 * 
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate (RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		// Replacing default serialization mode with Jackson2JsonRedisSerialize.
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = 
				new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		// Set the serialization rules of value and the serialization rules of key.
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
}
