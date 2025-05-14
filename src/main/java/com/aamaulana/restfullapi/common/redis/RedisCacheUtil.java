package com.aamaulana.restfullapi.common.redis;

import com.aamaulana.restfullapi.common.config.AppConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisCacheUtil {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final AppConfig appConfig;

    public <T> void cacheValue(String key, T value) {
        try {
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue, Duration.ofSeconds(appConfig.getRedisTtlInSecond()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    public <T> T getCachedValue(String key, Class<T> clazz) {
        try {
            String redisData = redisTemplate.opsForValue().get(key);
            if (redisData != null) {
                return objectMapper.readValue(redisData, clazz);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
        return null;
    }

    public <T> T getCachedList(String key, TypeReference<T> typeRef) {
        try {
            String redisData = redisTemplate.opsForValue().get(key);
            if (redisData != null) {
                return objectMapper.readValue(redisData, typeRef);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
        return null;
    }

    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }
}

