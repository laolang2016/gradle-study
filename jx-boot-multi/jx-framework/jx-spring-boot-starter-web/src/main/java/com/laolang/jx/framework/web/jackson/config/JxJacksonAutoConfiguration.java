package com.laolang.jx.framework.web.jackson.config;

import com.laolang.jx.framework.web.jackson.module.BigDecimalModule;
import com.laolang.jx.framework.web.jackson.module.Jdk8TimeModule;
import com.laolang.jx.framework.web.jackson.module.LongModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JxJacksonAutoConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.modulesToInstall(new BigDecimalModule(), new Jdk8TimeModule(), new LongModule());
        };
    }
}
