package org.example.entity.mapper.resident;

import org.example.entity.resident.MysticResidentEntity;
import org.example.model.resident.MysticResident;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MysticResidentMapper {
    MysticResident toDto(MysticResidentEntity entity);

    MysticResidentEntity toEntity(MysticResident dto);

    List<MysticResident> toDto(List<MysticResidentEntity> entities);

    List<MysticResidentEntity> toEntity(List<MysticResident> dtos);
}
