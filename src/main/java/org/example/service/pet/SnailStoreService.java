package org.example.service.pet;

import lombok.AllArgsConstructor;
import org.example.entity.mapper.pet.SnailMapper;
import org.example.entity.pet.SnailEntity;
import org.example.model.pet.Snail;
import org.example.repository.SnailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
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
        return repository.findById(id).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<SnailEntity> getEntityById(String id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Snail> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public List<SnailEntity> getAllEntity() {
        return repository.findAll();
    }

    public String save(Snail dto) {
        SnailEntity toSave = mapper.toEntity(dto);
        //todo autoset while saving
        toSave.setCreationTime(ZonedDateTime.now());
        toSave.setLastModifiedTime(ZonedDateTime.now());
        SnailEntity saved = repository.save(toSave);

        return saved.getId();
    }

    public void saveEntity(SnailEntity entity) {
        repository.save(entity);
    }
}
