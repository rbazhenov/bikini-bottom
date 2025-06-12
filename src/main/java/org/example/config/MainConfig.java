package org.example.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import jakarta.persistence.EntityManager;
import org.example.context.ContextConfiguration;
import org.example.controller.BpController;
import org.example.controller.mapper.BpMappers;
import org.example.entity.BpEntity;
import org.example.entity.mapper.BpEntityMappers;
import org.example.integration.BpIntegration;
import org.example.repository.BpRepository;
import org.example.scheduler.BpSheduler;
import org.example.security.WebSecurityConfiguration;
import org.example.service.BpService;
import org.example.service.aop.logging.LogConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@EnableFeignClients(basePackageClasses = BpIntegration.class)
@EnableConfigurationProperties
@EntityScan(basePackageClasses = BpEntity.class)
@EnableJpaRepositories(basePackageClasses = BpRepository.class)
@ComponentScan(basePackageClasses = {
        BpService.class,
        BpController.class,
        BpEntityMappers.class,
        BpMappers.class,
        BpSheduler.class,
})
@Import(value = {
        LogConfig.class,
        WebSecurityConfiguration.class,
        ContextConfiguration.class,
//        MyConfiguration.class
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

    @Bean
    public StatefulRedisConnection<String, String> redisConnection(@Value("${redis.url}") String url,
                                                                   @Value("${redis.port}") Integer port) {
        RedisURI redisURI = RedisURI.Builder.redis(url, port).build();
        RedisClient redisClient = RedisClient.create(redisURI);

        return redisClient.connect();
    }
}
