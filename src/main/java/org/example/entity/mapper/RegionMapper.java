package org.example.entity.mapper;

import org.example.entity.RegionEntity;
import org.example.model.dictionary.Region;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    Region toDto(RegionEntity entity);

    RegionEntity toEntity(Region dto);

    List<Region> toDto(List<RegionEntity> entities);

    List<RegionEntity> toEntity(List<Region> dtos);
}
