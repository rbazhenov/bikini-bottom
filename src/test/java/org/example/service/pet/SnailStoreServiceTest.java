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
    private SnailStoreService storeService;

    @Test
    void updateLastModifiedTest() {
        Snail snail = new Snail();
        snail.setAge(10);
        String id = storeService.save(snail);

        SnailEntity entity = storeService.getEntityById(id).get();
        ZonedDateTime lmtBefore = entity.getLastModifiedTime();

        entity.setAge(11);
        storeService.saveEntity(entity);

        ZonedDateTime lmtAfter = storeService.getEntityById(id).get().getLastModifiedTime();

        Assertions.assertNotEquals(lmtBefore, lmtAfter);
    }
}
