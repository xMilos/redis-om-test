package com.example.demo.redis;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class RedisContainer implements DisposableBean {

    protected static final GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis/redis-stack-server"))
            .withExposedPorts(6379)
            .withReuse(true);

    static {
        redis.start();
        log.debug("Started Redis container on {}:{}", redis.getHost(), redis.getFirstMappedPort());
    }

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redis::getHost);
        registry.add("spring.data.redis.port", redis::getFirstMappedPort);
    }

    @Test
    @Order(1)
    void checkRedisContainerIsRunning() {
        assertTrue(redis.isRunning());
    }

    @Override
    public void destroy() {
        log.debug("Stopping Redis container");
        redis.stop();
    }
}

