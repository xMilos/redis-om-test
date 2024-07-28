package com.example.demo.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@ContextConfiguration
class ExampleProjectTest extends RedisContainer {

    @Autowired
    RedisRepository redisRepository;

    @BeforeEach
    void setUp() {
        // Clear the repository before each test
        redisRepository.deleteAll();
        List<ExampleProject> list = Arrays.asList(
                new ExampleProject(1L, 0, "Project A"),
                new ExampleProject(2L, 0, "Project B"),
                new ExampleProject(3L, 1, "Project C"),
                new ExampleProject(4L, 1, "Project D"),
                new ExampleProject(5L, 1, "Project E")
        );
        // Populate the repository with sample data
        redisRepository.saveAll(list);
    }

    @Test
    void testGetAllRedisGeneric() {
        Set<ExampleProject> data = redisRepository.findAllFields(ExampleProject.class, PageRequest.of(1, 4), ExampleProject$.ID, ExampleProject$.IS_DELETED);
        assertEquals(1, data.size());
        assertEquals(5L, data.stream().findFirst().get().getId());
        assertEquals(1, data.stream().findFirst().get().getIsDeleted());
        assertNull(data.stream().findFirst().get().getName());
    }
}