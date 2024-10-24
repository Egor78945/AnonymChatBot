package org.example.anonymchatbot.configuration.redis.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisEnvironment {
    private final String REDIS_HOST;
    private final int REDIS_PORT;

    public RedisEnvironment(@Value("${spring.data.redis.host}") String REDIS_HOST, @Value("${spring.data.redis.port}") int REDIS_PORT) {
        this.REDIS_HOST = REDIS_HOST;
        this.REDIS_PORT = REDIS_PORT;
    }

    public int getREDIS_PORT() {
        return REDIS_PORT;
    }

    public String getREDIS_HOST() {
        return REDIS_HOST;
    }
}
