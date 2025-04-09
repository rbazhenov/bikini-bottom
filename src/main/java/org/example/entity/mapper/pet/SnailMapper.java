package org.example.entity.mapper.pet;

import org.example.entity.pet.SnailEntity;
import org.example.model.pet.Snail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SnailMapper {
    Snail toDto(SnailEntity entity);

    SnailEntity toEntity(Snail dto);

    List<Snail> toDto(List<SnailEntity> entities);

    List<SnailEntity> toEntity(List<Snail> dtos);
}
