package org.example.controller.mapper.resident;

import org.example.model.resident.MysticResident;
import org.mapstruct.Mapper;
import www.ru.bikini_bottom.api.swagger.model.MysticResidentDto;

@Mapper(componentModel = "spring")
public interface MysticResidentDtoMapper extends ResidentDtoMapper<MysticResident, MysticResidentDto> {

    @Override
    default Class<MysticResident> getModelClass() {
        return MysticResident.class;
    }

    @Override
    default Class<MysticResidentDto> getDtoClass() {
        return MysticResidentDto.class;
    }

}
