package com.litsynp.redisdemo.ex4jpacache.api;

import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import com.litsynp.redisdemo.ex4jpacache.service.MemberService;
import com.litsynp.redisdemo.ex4jpacache.dto.MemberCreateRequestDto;
import com.litsynp.redisdemo.ex4jpacache.dto.MemberResponseDto;
import com.litsynp.redisdemo.ex4jpacache.dto.MemberUpdateRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v4/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> create(@RequestBody MemberCreateRequestDto dto) {
        Member created = memberService.create(dto.toEntity());
        MemberResponseDto response = MemberResponseDto.of(created);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<MemberResponseDto>> list(
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
        Page<Member> members = memberService.findAll(pageable);
        Page<MemberResponseDto> response = members.map(MemberResponseDto::of);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> detail(@PathVariable UUID id) {
        Member found = memberService.findById(id);
        MemberResponseDto response = MemberResponseDto.of(found);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> update(
            @PathVariable UUID id,
            @RequestBody MemberUpdateRequestDto dto) {
        Member updated = memberService.update(id, dto.toEntity());
        MemberResponseDto response = MemberResponseDto.of(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
