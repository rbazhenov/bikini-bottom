package org.example.entity.mapper.resident;

import org.example.entity.resident.LocalResidentEntity;
import org.example.model.resident.LocalResident;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocalResidentMapper {
    LocalResident toDto(LocalResidentEntity entity);

    LocalResidentEntity toEntity(LocalResident dto);

    List<LocalResident> toDto(List<LocalResidentEntity> entities);

    List<LocalResidentEntity> toEntity(List<LocalResident> dtos);
}
