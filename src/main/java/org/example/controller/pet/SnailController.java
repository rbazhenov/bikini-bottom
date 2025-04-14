package org.example.controller.pet;

import lombok.AllArgsConstructor;
import org.example.controller.PetController;
import org.example.controller.mapper.SnailMapper;
import org.example.service.aop.logging.annotation.LogMethodCall;
import org.example.service.pet.SnailStoreService;
import org.springframework.http.ResponseEntity;
import www.ru.bikini_bottom.api.swagger.api.SnailApi;
import www.ru.bikini_bottom.api.swagger.model.SnailDto;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@PetController
@AllArgsConstructor
public class SnailController implements SnailApi {

    private final SnailStoreService storeService;
    private final SnailMapper mapper;

    @Override
    @LogMethodCall
    public ResponseEntity<SnailDto> saveSnail(@Valid SnailDto dto) {
        return Optional.ofNullable(dto)
                .map(mapper::toModel)
                .map(storeService::save)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @LogMethodCall
    public ResponseEntity<SnailDto> getSnailById(@Size(max = 255) String id) {
        return storeService.getById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @LogMethodCall
    public ResponseEntity<List<SnailDto>> listSnail() {
        return ResponseEntity.ok(mapper.toDto(storeService.getAll()));
    }

}
