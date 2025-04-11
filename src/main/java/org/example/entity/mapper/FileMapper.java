package org.example.entity.mapper;

import org.example.entity.FileEntity;
import org.example.model.file.File;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    File toModel(FileEntity entity);

    FileEntity toEntity(File model);

    List<File> toModel(List<FileEntity> entities);

    List<FileEntity> toEntity(List<File> models);
}
