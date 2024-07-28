package com.example.demo.redis;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.NumericIndexed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(force = true)
@Document
public class ExampleProject {
    @Id
    @NonNull
    private Long id;

    @NumericIndexed
    private Integer isDeleted;

    private String name;
}