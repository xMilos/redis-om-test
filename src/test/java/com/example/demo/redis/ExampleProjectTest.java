package com.example.demo.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
class ExampleProjectTest extends AbstractDocumentTest{

    @Autowired
    RedisRepository redisRepository;

    @BeforeEach
    void setUp() {
        // Clear the repository before each test
        redisRepository.deleteAll();
        List<ExampleProject> list = new ArrayList<>();
        for (long i = 1; i <= 100000; i++) {
            int status = (i % 2 == 0) ? 0 : 1; // Alternating status for example
            String name = "Project " + i;
            list.add(new ExampleProject(i, status, name));
        }
        // Populate the repository with sample data
        redisRepository.saveAll(list);
    }

    @Test
    void testGetAllRedisGeneric() {
        Set<ExampleProject> data = redisRepository.findAllFields(ExampleProject.class, PageRequest.of(1, 2), ExampleProject$.ID, ExampleProject$.IS_DELETED);
        assertEquals(2, data.size());
        assertEquals(3L, data.stream().findFirst().get().getId());
        assertEquals(1, data.stream().findFirst().get().getIsDeleted());
        assertEquals(null, data.stream().findFirst().get().getName());
    }
}