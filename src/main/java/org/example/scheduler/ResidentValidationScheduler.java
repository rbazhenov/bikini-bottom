package org.example.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.integration.bikini.validation.BikiniValidationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor
@ConditionalOnProperty(prefix = "scheduler", value = "enabled", havingValue = "true")
public class ResidentValidationScheduler {

    private final BikiniValidationService validationService;

    @Scheduled(cron = "#{validationScheduledProperties.cron}")
    public void checkResidents() {
        log.info("Start ResidentValidationScheduler");
        validationService.checkAllResident();
        log.info("Finish ResidentValidationScheduler");
    }
}
