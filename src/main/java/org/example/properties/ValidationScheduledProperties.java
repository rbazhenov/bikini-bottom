package org.example.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "scheduler.validation")
public class ValidationScheduledProperties {

    private String cron;

    @PostConstruct
    void Info() {
        log.debug("ValidationScheduledProperties {}", this);
    }

}


