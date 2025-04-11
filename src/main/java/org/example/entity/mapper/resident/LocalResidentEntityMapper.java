package org.example.entity.mapper.resident;

import org.example.entity.resident.LocalResidentEntity;
import org.example.model.resident.LocalResident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalResidentEntityMapper extends ResidentEntityMapper<LocalResidentEntity, LocalResident> {
    @Override
    default Class<LocalResidentEntity> getEntityClass(){
        return LocalResidentEntity.class;
    }

    @Override
    default Class<LocalResident> getModelClass(){
        //todo rbs check is it need?
        return LocalResident.class;
    };
}
