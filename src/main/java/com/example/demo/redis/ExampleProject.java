package com.example.demo.redis;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.NumericIndexed;
import lombok.*;
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