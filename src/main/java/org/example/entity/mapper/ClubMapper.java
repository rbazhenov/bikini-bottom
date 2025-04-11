package org.example.entity.mapper;

import org.example.entity.ClubEntity;
import org.example.model.resident.Club;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubMapper {
    Club toModel(ClubEntity entity);

    ClubEntity toEntity(Club model);

    List<Club> toModel(List<ClubEntity> entities);

    List<ClubEntity> toEntity(List<Club> models);
}
