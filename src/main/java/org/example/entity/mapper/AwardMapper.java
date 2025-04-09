package org.example.entity.mapper;

import org.example.entity.AwardEntity;
import org.example.model.award.Award;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AwardMapper {
    Award toDto(AwardEntity entity);

    AwardEntity toEntity(Award dto);

    List<Award> toDto(List<AwardEntity> entities);

    List<AwardEntity> toEntity(List<Award> dtos);
}
