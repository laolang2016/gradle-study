package com.laolang.jx;

import com.yomahub.tlog.core.enhance.bytes.AspectLogEnhance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JxApplication {
    static {
        AspectLogEnhance.enhance();
    }

    public static void main(String[] args) {
        SpringApplication.run(JxApplication.class, args);
        log.info("jx is running...");
    }
}
