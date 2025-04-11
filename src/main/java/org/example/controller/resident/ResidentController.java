package org.example.controller.resident;

import lombok.AllArgsConstructor;
import org.example.controller.mapper.ResidentDtoMapperService;
import org.example.service.resident.ResidentServiceResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import www.ru.bikini_bottom.api.swagger.api.ResidentApi;
import www.ru.bikini_bottom.api.swagger.model.ResidentDto;
import www.ru.bikini_bottom.api.swagger.model.ResidentDtoBody;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
public class ResidentController implements ResidentApi {

    private final ResidentDtoMapperService mapperService;
    private final ResidentServiceResolver serviceResolver;

    @Override
    public ResponseEntity<ResidentDto> saveResident(@NotNull @Valid ResidentDtoBody dto) {
        return Optional.ofNullable(dto)
                .map(ResidentDto.class::cast)
                .map(mapperService::toModel)
                .map(model -> serviceResolver.getByResident(model).save(model))
                .map(Optional::get)
                .map(mapperService::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
