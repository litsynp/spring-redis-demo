package com.litsynp.redisdemo.ex1redistemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/str-str")
@RequiredArgsConstructor
public class RedisController {

    // Same as RedisTemplate<String, String> but with string serializers
    private final StringRedisTemplate redisTemplate;

    @PostMapping
    public ResponseEntity<Void> addRedisKey(@RequestBody AddRequestDto dto) {
        ValueOperations<String, String> vops = redisTemplate.opsForValue();
        vops.set(dto.getKey(), dto.getValue());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{key}")
    public ResponseEntity<GetResponseDto> getRedisKey(@PathVariable String key) {
        ValueOperations<String, String> vops = redisTemplate.opsForValue();
        String value = vops.get(key);
        if (value != null) {
            return ResponseEntity.ok(new GetResponseDto(key, value, true));
        } else {
            return ResponseEntity.ok(new GetResponseDto(key, null, false));
        }
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class AddRequestDto {

        private String key;
        private String value;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class GetResponseDto {

        private String key;
        private String value;
        private boolean success;
    }
}
