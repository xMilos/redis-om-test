package com.example.demo.redis;

import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends RedisDocumentRepository<ExampleProject, Long>, ExampleProjectRepository {

}
