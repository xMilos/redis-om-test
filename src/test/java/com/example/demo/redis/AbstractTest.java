package com.example.demo.redis;

import com.redis.om.spring.ops.RedisModulesOperations;
import com.redis.testcontainers.RedisStackContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.redis.testcontainers.RedisStackContainer.DEFAULT_IMAGE_NAME;

@Testcontainers(disabledWithoutDocker = true)
@DirtiesContext
public abstract class AbstractTest {
  @Container
  static final RedisStackContainer REDIS;

  static {
    REDIS = new RedisStackContainer(DEFAULT_IMAGE_NAME.withTag("edge")).withReuse(true);
    REDIS.start();
  }

  @Autowired
  protected StringRedisTemplate template;

  @Autowired
  protected RedisModulesOperations<String> modulesOperations;



  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", REDIS::getHost);
    registry.add("spring.redis.port", REDIS::getFirstMappedPort);
  }

}
