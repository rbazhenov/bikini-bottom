package org.example.service.resident;

import lombok.AllArgsConstructor;
import org.example.entity.mapper.resident.LocalResidentMapper;
import org.example.entity.resident.LocalResidentEntity;
import org.example.model.resident.LocalResident;
import org.example.repository.LocalResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LocalResidentStoreService {

    private LocalResidentRepository repository;
    private LocalResidentMapper mapper;

    @Transactional(readOnly = true)
    public Optional<LocalResident> getById(String id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<LocalResidentEntity> getEntityById(String id) {
        return repository.findById(id);
    }

    @Transactional
    public String save(LocalResident dto) {
        LocalResidentEntity toSave = mapper.toEntity(dto);
        return saveEntity(toSave);
    }

    public String saveEntity(LocalResidentEntity entity) {
        return repository.save(entity).getId();
    }
}
