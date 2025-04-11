package org.example.controller.mapper.resident;

import org.example.model.resident.LocalResident;
import org.mapstruct.Mapper;
import www.ru.bikini_bottom.api.swagger.model.LocalResidentDto;

@Mapper(componentModel = "spring")
public interface LocalResidentDtoMapper extends ResidentDtoMapper<LocalResident, LocalResidentDto> {

    @Override
    default Class<LocalResident> getModelClass() {
        return LocalResident.class;
    }

    @Override
    default Class<LocalResidentDto> getDtoClass() {
        return LocalResidentDto.class;
    }
}
