package org.example.controller.mapper.resident;

import org.example.model.resident.Resident;
import www.ru.bikini_bottom.api.swagger.model.ResidentDto;

public interface ResidentDtoMapper<Model extends Resident, Dto extends ResidentDto> {

    Class<Model> getModelClass();

    Class<Dto> getDtoClass();

    Model toModel(Dto dto);

    Dto toDto(Model model);
}
