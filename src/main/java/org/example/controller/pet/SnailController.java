package org.example.controller.pet;

import lombok.AllArgsConstructor;
import org.example.controller.mapper.SnailMapper;
import org.example.model.pet.Snail;
import org.example.service.pet.SnailStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import www.ru.bikini_bottom.api.swagger.api.SnailApi;
import www.ru.bikini_bottom.api.swagger.model.SnailDto;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
//todo RBS-7 logging controllers with aop
public class SnailController implements SnailApi {

    private SnailStoreService storeService;
    private SnailMapper mapper;

    //todo rbs create test
    @Override
    public ResponseEntity<Void> saveSnail(@Valid SnailDto dto) {
        storeService.save(mapper.toModel(dto));
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Snail>> getAll() {
//        return ResponseEntity.ok(storeService.getAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Snail>> getOne(@PathVariable(value = "id") String id) {
//        return ResponseEntity.ok(storeService.getById(id));
//    }


}
