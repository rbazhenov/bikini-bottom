package org.example.integration.bikini.validation;

import lombok.AllArgsConstructor;
import org.example.entity.resident.ResidentEntity;
import org.example.exception.ResidentNotFoundException;
import org.example.model.resident.CheckResult;
import org.example.model.resident.ResidentCheckAnswer;
import org.example.repository.ResidentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import www.ru.bikini_validation.api.swagger.model.ResidentCheckResponse;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BikiniValidationService {

    private final BikiniValidationClient validationClient;
    private final ResidentRepository residentRepository;

    @KafkaListener(
            containerFactory = "validationContainerFactory",
            topics = "#{@validationProperties.getTopic()}",
            groupId = "#{@validationProperties.getGroupId()}",
            errorHandler = "validationErrorHandler"
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void listenResidentValidationResult(@Payload ResidentCheckAnswer answer,
                                               @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        String checkId = answer.getCheckId();
        CheckResult result = answer.getResult();
        Optional<ResidentEntity> optionalResident = residentRepository.findByValidationId(checkId);
        if (optionalResident.isEmpty()) {
            throw new ResidentNotFoundException("Check id = " + checkId);
        }

        ResidentEntity entity = optionalResident.get();
        entity.setValidated(CheckResult.SUCCESS == result);
        residentRepository.save(entity);
    }

    //todo rbs integration mock test
    @Transactional
    public ResidentCheckResponse checkResident(String id) {
        Optional<ResidentEntity> optionalResident = residentRepository.findById(id);
        if (optionalResident.isEmpty()) {
            throw new ResidentNotFoundException("Id = " + id);
        }

        return startCheck(optionalResident.get());
    }

    @Transactional
    public void checkAllResident() {
        residentRepository.findAll()
                .forEach(this::startCheck);
    }

    private ResidentCheckResponse startCheck(ResidentEntity entity) {
        ResidentCheckResponse checkResponse = validationClient.checkResident(entity.getId()).getBody();
        String checkId = checkResponse.getCheckId();

        entity.setValidationId(checkId);
        residentRepository.save(entity);

        return checkResponse;
    }
}
