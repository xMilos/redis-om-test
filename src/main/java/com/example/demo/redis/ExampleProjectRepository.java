package com.example.demo.redis;

import com.redis.om.spring.metamodel.MetamodelField;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface ExampleProjectRepository {
    <E> Set<E> findAllFields(Class<E> clazz, Pageable pageable, MetamodelField<E, ?>... fields);
}

