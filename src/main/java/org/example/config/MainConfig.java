package org.example.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.example.context.ContextConfiguration;
import org.example.controller.BpController;
import org.example.controller.mapper.BpMappers;
import org.example.entity.BpEntity;
import org.example.entity.mapper.BpEntityMappers;
import org.example.repository.BpRepository;
import org.example.security.WebSecurityConfiguration;
import org.example.service.BpService;
import org.example.service.aop.logging.LogConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
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
@EntityScan(basePackageClasses = BpEntity.class)
@EnableJpaRepositories(basePackageClasses = BpRepository.class)
@ComponentScan(basePackageClasses = {
        BpService.class,
        BpController.class,
        BpEntityMappers.class,
        BpMappers.class,
})
@Import(value = {
        LogConfig.class,
        WebSecurityConfiguration.class,
        ContextConfiguration.class,
})
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@EnableAspectJAutoProxy
public class MainConfig {

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }

    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
