package com.example.demo.redis;

import com.redis.om.spring.metamodel.MetamodelField;
import com.redis.om.spring.search.stream.EntityStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.stream.Collectors;

public class ExampleProjectRepositoryImpl implements ExampleProjectRepository {

    @Autowired
    EntityStream entityStream;

    @Override
    public <E> Set<E> findAllFields(Class<E> clazz, Pageable pageable, MetamodelField<E, ?>... fields) {
        Set<E> collect = entityStream.of(clazz)
                .skip(pageable.getPageNumber() * pageable.getPageSize()) // page number
                .limit(pageable.getPageSize()) // page size
                .project(fields)
                .collect(Collectors.toSet());
        return collect;
    }
}
