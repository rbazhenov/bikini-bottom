package org.example.service.pet;

import lombok.AllArgsConstructor;
import org.example.entity.mapper.pet.SnailMapper;
import org.example.entity.pet.SnailEntity;
import org.example.model.pet.Snail;
import org.example.repository.SnailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SnailStoreService {

    private SnailRepository repository;
    private SnailMapper mapper;

    @Transactional(readOnly = true)
    public Optional<Snail> getById(String id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Transactional(readOnly = true)
    public Optional<SnailEntity> getEntityById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Snail> getAll() {
        return mapper.toModel(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<SnailEntity> getAllEntity() {
        return repository.findAll();
    }

    public Snail save(Snail dto) {
        SnailEntity toSave = mapper.toEntity(dto);
        SnailEntity saved = saveEntity(toSave);
        return mapper.toModel(saved);
    }

    public SnailEntity saveEntity(SnailEntity entity) {
        return repository.save(entity);
    }
}
