package org.example.controller.mapper;

import org.example.model.pet.Snail;
import org.mapstruct.Mapper;
import www.ru.bikini_bottom.api.swagger.model.SnailDto;

import java.util.List;

@Mapper(componentModel = "spring",
        implementationName = "SnailMapper_V1")
public interface SnailMapper {
    Snail toModel(SnailDto dto);

    SnailDto toDto(Snail model);

    List<SnailDto> toDto(List<Snail> models);
}
