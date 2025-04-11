package org.example.entity.mapper;

import org.example.entity.RegionEntity;
import org.example.model.dictionary.Region;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    Region toModel(RegionEntity entity);

    RegionEntity toEntity(Region model);

    List<Region> toModel(List<RegionEntity> entities);

    List<RegionEntity> toEntity(List<Region> models);
}
