package com.wani.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainApplication {
    public void contextLoads() {
    }


    @Test
    void name() {
        Assertions.assertThat("1").isEqualTo("1");
    }
}
