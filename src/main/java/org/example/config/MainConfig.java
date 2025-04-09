package org.example.config;

import org.example.controller.BpController;
import org.example.controller.mapper.BpMappers;
import org.example.entity.BpEntity;
import org.example.entity.mapper.BpEntityMappers;
import org.example.repository.BpRepository;
import org.example.service.BpService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EnableKafka
@EnableAsync
//@EnableFeignClients todo RBS-3
//@EnableConfigurationProperties todo RBS-4
//@EnableJpaAuditing //todo RBS-5
@EntityScan(basePackageClasses = BpEntity.class)
@EnableJpaRepositories(basePackageClasses = BpRepository.class)
@ComponentScan(basePackageClasses = {
        BpService.class,
        BpController.class,
        BpEntityMappers.class,
        BpMappers.class,
})
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class MainConfig {

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }
}
