package org.example.service.aop.logging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = LogConfig.class)
public class LogConfig {

    @Bean
    @ConditionalOnProperty(value = "log.enabled", havingValue = "true")
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
