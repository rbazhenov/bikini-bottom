package org.example.service.pet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
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

    private final static Long TTL_SECONDS = 10L;
    private final SnailRepository repository;
    private final SnailMapper mapper;
    private final StatefulRedisConnection<String, String> redisConnection;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Snail> getCashedById(String id) {
        try {
            //todo rbs use async
            RedisCommands<String, String> syncCommands = redisConnection.sync();
            String key = "Snail:%s".formatted(id);
            String value = syncCommands.get(key);
            if (value != null) {
                return Optional.of(objectMapper.readValue(value, Snail.class));
            }

            Optional<Snail> entity = getById(id);
            if (entity.isPresent()) {
                syncCommands.setex(key, TTL_SECONDS, objectMapper.writeValueAsString(entity.get()));
            }

            return entity;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

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
