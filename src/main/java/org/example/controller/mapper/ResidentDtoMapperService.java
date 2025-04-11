package org.example.controller.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.mapper.resident.ResidentDtoMapper;
import org.example.model.resident.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import www.ru.bikini_bottom.api.swagger.model.ResidentDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@SuppressWarnings("rawtypes")
public class ResidentDtoMapperService {

    public Map<Class<? extends Resident>, ResidentDtoMapper> mapperToDto = new HashMap<>();
    public Map<Class<? extends ResidentDto>, ResidentDtoMapper> mapperToModel = new HashMap<>();

    @Autowired
    public void setMapperMap(Set<ResidentDtoMapper> mappers) {
        if (CollectionUtils.isEmpty(mappers)) {
            log.error("mappers is empty");
            throw new RuntimeException("mappers is empty");
        }
        for (ResidentDtoMapper mapper : mappers) {
            if (mapperToDto.containsKey(mapper.getModelClass())) {
                log.warn("Mapper to dto {} is doubled, skip one", mapper.getModelClass().getSimpleName());
            } else {
                log.info("Put toDto mapper {}", mapper.getModelClass().getSimpleName());
                mapperToDto.put(mapper.getModelClass(), mapper);
            }

            if (mapperToModel.containsKey(mapper.getDtoClass())) {
                log.warn("Mapper from dto {} is doubled, skip one", mapper.getModelClass().getSimpleName());

            } else {
                log.info("Put fromDto mapper {}", mapper.getModelClass().getSimpleName());
                mapperToModel.put(mapper.getDtoClass(), mapper);
            }
        }
    }

    public List<ResidentDto> toDtos(List<? extends Resident> residents) {
        if (CollectionUtils.isEmpty(residents)) {
            return Collections.emptyList();
        }

        return residents.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ResidentDto toDto(Resident resident) {
        if (resident == null) {
            return null;
        }

        final ResidentDtoMapper mapper = getToDtoMapper(resident.getClass());
        return mapper.toDto(resident);
    }

    public Resident toModel(ResidentDto dto) {
        if (dto == null) {
            return null;
        }

        final ResidentDtoMapper mapper = getToModelMapper(dto.getClass());
        return mapper.toModel(dto);
    }

    private ResidentDtoMapper getToModelMapper(Class clazz) {
        ResidentDtoMapper mapper = mapperToModel.get(clazz);
        checkMapperPresence(mapper, clazz);
        return mapper;
    }

    private ResidentDtoMapper getToDtoMapper(Class clazz) {
        ResidentDtoMapper mapper = mapperToDto.get(clazz);
        checkMapperPresence(mapper, clazz);
        return mapper;
    }

    private void checkMapperPresence(ResidentDtoMapper mapper, Class clazz) {
        if (mapper == null) {
            log.error("Resident type not supported {}", clazz.getSimpleName());
            throw new UnsupportedOperationException("Resident type not supported");
        }
    }
}
