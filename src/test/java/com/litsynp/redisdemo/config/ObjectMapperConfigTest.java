package com.litsynp.redisdemo.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperConfigTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void objectMapperTest() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 18, 21, 10, 0);
        String timeStr = objectMapper.writeValueAsString(localDateTime);
        assertThat(timeStr).isEqualTo("\"2022-06-18T21:10:00\"");
    }
}
