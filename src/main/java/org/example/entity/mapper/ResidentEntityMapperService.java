package org.example.entity.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.mapper.resident.ResidentEntityMapper;
import org.example.entity.resident.ResidentEntity;
import org.example.model.resident.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@SuppressWarnings("rawtypes")
public class ResidentEntityMapperService {

    public Map<Class<? extends ResidentEntity>, ResidentEntityMapper> mapperToModel = new HashMap<>();
    public Map<Class<? extends Resident>, ResidentEntityMapper> mapperToEntity = new HashMap<>();

    @Autowired
    public void setMapperMap(Set<ResidentEntityMapper> mappers) {
        if (CollectionUtils.isEmpty(mappers)) {
            log.error("mappers is empty");
            throw new RuntimeException("mappers is empty");
        }
        for (ResidentEntityMapper mapper : mappers) {
            if (mapperToEntity.containsKey(mapper.getModelClass())) {
                log.warn("Mapper to entity {} is doubled, skip one", mapper.getModelClass().getSimpleName());
            } else {
                log.info("Put toEntity mapper {}", mapper.getModelClass().getSimpleName());
                mapperToEntity.put(mapper.getModelClass(), mapper);
            }

            if (mapperToModel.containsKey(mapper.getEntityClass())) {
                log.warn("Mapper to model {} is doubled, skip one", mapper.getModelClass().getSimpleName());

            } else {
                log.info("Put toModel mapper {}", mapper.getModelClass().getSimpleName());
                mapperToModel.put(mapper.getEntityClass(), mapper);
            }
        }
    }

    public List<Resident> toModels(List<? extends ResidentEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Resident toModel(ResidentEntity entity) {
        if (entity == null) {
            return null;
        }

        final ResidentEntityMapper mapper = getToModelMapper(entity.getClass());
        return mapper.toModel(entity);
    }

    public ResidentEntity toEntity(Resident model) {
        if (model == null) {
            return null;
        }

        final ResidentEntityMapper mapper = getToEntityMapper(model.getClass());
        return mapper.toEntity(model);
    }

    private ResidentEntityMapper getToModelMapper(Class clazz) {
        ResidentEntityMapper mapper = mapperToModel.get(clazz);
        checkMapperPresence(mapper, clazz);
        return mapper;
    }

    private ResidentEntityMapper getToEntityMapper(Class clazz) {
        ResidentEntityMapper mapper = mapperToEntity.get(clazz);
        checkMapperPresence(mapper, clazz);
        return mapper;
    }

    private void checkMapperPresence(ResidentEntityMapper mapper, Class clazz) {
        if (mapper == null) {
            log.error("Resident type not supported {}", clazz.getSimpleName());
            throw new UnsupportedOperationException("Resident type not supported");
        }
    }
}
