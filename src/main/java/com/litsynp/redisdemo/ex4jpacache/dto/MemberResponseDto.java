package com.litsynp.redisdemo.ex4jpacache.dto;

import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import java.time.LocalDateTime;
import java.util.UUID;
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
public class MemberResponseDto {

    private UUID id;
    private String name;
    private Integer age;
    private LocalDateTime createdAt;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
