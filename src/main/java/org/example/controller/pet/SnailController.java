package org.example.controller.pet;

import lombok.AllArgsConstructor;
import org.example.model.pet.Snail;
import org.example.service.pet.SnailStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(SnailController.PATH)
//todo RBS-1 добавить слой дто от опенапи
//todo RBS-7 logging controllers with aop
public class SnailController {

    public static final String PATH = "/api/pet/snail/";

    private SnailStoreService storeService;

    @GetMapping("/list")
    public ResponseEntity<List<Snail>> getAll() {
        return ResponseEntity.ok(storeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Snail>> getOne(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(storeService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody Snail dto) {
        storeService.save(dto);
        return ResponseEntity.ok().build();
    }

}
