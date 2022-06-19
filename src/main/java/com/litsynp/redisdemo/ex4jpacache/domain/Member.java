package com.litsynp.redisdemo.ex4jpacache.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(length = 30)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Member(String name, Integer age, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
    }

    public void update(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
