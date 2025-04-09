package org.example.service.pet;

import org.example.SpringApplicationTest;
import org.example.entity.pet.SnailEntity;
import org.example.model.pet.Snail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

@SpringApplicationTest
class SnailStoreServiceTest {

    @Autowired
    private SnailStoreService service;

    @Test
    void updateLastModifiedTest() {
        Snail snail = new Snail();
        snail.setAge(10);
        String id = service.save(snail);

        SnailEntity entity = service.getEntityById(id).get();
        ZonedDateTime lmtBefore = entity.getLastModifiedTime();

        entity.setAge(11);
        service.saveEntity(entity);

        ZonedDateTime lmtAfter = service.getEntityById(id).get().getLastModifiedTime();

        Assertions.assertNotEquals(lmtBefore, lmtAfter);
    }
}
