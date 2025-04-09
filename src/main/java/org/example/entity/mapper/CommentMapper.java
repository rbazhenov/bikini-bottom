package org.example.entity.mapper;

import org.example.entity.CommentEntity;
import org.example.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toDto(CommentEntity entity);

    CommentEntity toEntity(Comment dto);

    List<Comment> toDto(List<CommentEntity> entities);

    List<CommentEntity> toEntity(List<Comment> dtos);
}
