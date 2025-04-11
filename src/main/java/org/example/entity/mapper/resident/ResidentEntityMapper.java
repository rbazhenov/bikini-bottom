package org.example.entity.mapper.resident;

import org.example.entity.resident.ResidentEntity;
import org.example.model.resident.Resident;

import java.util.List;

public interface ResidentEntityMapper<Entity extends ResidentEntity, Model extends Resident> {

    Class<Entity> getEntityClass();

    Class<Model> getModelClass();

    Entity toEntity(Model model);

    Model toModel(Entity entity);

    List<Entity> toEntity(List<Model> model);

    List<Model> toModel(List<Entity> entity);
}
