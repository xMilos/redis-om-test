package com.example.demo.redis;

import com.redis.om.spring.metamodel.MetamodelField;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ExampleProjectRepository {
    <E> Set<E> findAllFields(Class<E> clazz, Pageable pageable, MetamodelField<E, ?>... fields);
}

