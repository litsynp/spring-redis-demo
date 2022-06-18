package com.litsynp.redisdemo.ex4jpacache.dto;

import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateRequestDto {

    private String name;
    private Integer age;

    public Member toEntity() {
        return Member.builder()
                .name(this.getName())
                .age(this.getAge())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
