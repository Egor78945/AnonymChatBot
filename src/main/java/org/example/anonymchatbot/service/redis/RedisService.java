package org.example.anonymchatbot.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final JsonMapper jsonMapper;

    public void saveObject(String key, Object value) throws JsonProcessingException {
        redisTemplate.opsForValue().set(key, jsonMapper.writeValueAsString(value));
    }

    public String getObject(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void saveToHash(String key, String hashKey, Object value) throws JsonProcessingException {
        redisTemplate.opsForHash().put(key, hashKey, jsonMapper.writeValueAsString(value));
    }

    public String getFromHash(String key, String hashKey) {
        return (String) redisTemplate.opsForHash().get(key, hashKey);
    }

    public <T> T readValueAs(String jsonString, Class<T> classTo) throws JsonProcessingException {
        return jsonMapper.readValue(jsonString, classTo);
    }
}
