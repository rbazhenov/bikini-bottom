package org.example.entity.mapper.resident;

import org.example.entity.resident.MysticResidentEntity;
import org.example.model.resident.MysticResident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MysticResidentEntityMapper extends ResidentEntityMapper<MysticResidentEntity, MysticResident> {
    @Override
    default Class<MysticResidentEntity> getEntityClass() {
        return MysticResidentEntity.class;
    }

    @Override
    default Class<MysticResident> getModelClass() {
        return MysticResident.class;
    }
}
