package org.example.entity.mapper;

import org.example.entity.AwardEntity;
import org.example.model.award.Award;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AwardMapper {
    Award toModel(AwardEntity entity);

    AwardEntity toEntity(Award model);

    List<Award> toModel(List<AwardEntity> entities);

    List<AwardEntity> toEntity(List<Award> models);
}
