package com.litsynp.redisdemo.ex1redistemplate;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCache implements Serializable {

    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime createdAt;

    public MemberCache(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public MemberCache(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
}
