package org.example.service.resident.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.mapper.ResidentEntityMapperService;
import org.example.model.resident.Resident;
import org.example.service.resident.ResidentEntityService;
import org.example.service.resident.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
@Getter
@Setter(onMethod = @__(@Autowired))
public abstract class ResidentServiceImpl<T extends Resident> implements ResidentService<T> {

    //todo rbs-10 I18N MessageResolverService
//    public static final String I18N_CUSTOMS_APP_TYPE = "customs.application.type.";

    protected ResidentEntityService entityService;
    protected ResidentEntityMapperService mapperService;

    protected Optional<Resident> saveBase(Resident resident) {
        return Optional.of(resident)
                .map(mapperService::toEntity)
                .map(entityService::save)
                .map(mapperService::toModel);
    }
}
