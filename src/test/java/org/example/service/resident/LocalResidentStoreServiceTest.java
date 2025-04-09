package org.example.service.resident;

import org.example.SpringApplicationTest;
import org.example.entity.FullName;
import org.example.entity.resident.LocalResidentEntity;
import org.example.model.resident.LocalResident;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

@SpringApplicationTest
class LocalResidentStoreServiceTest {

    @Autowired
    private LocalResidentStoreService service;

    @Test
    void updateLastModifiedTest() {
        FullName fullName = new FullName();
        fullName.setFirstName("local");
        fullName.setLastName("ship");
        LocalResident localResident = new LocalResident();
        localResident.setFullName(fullName);
        String id = service.save(localResident);

        LocalResidentEntity entity = service.getEntityById(id).get();
        ZonedDateTime lmtBefore = entity.getLastModifiedTime();

        String newName = "newName";
        fullName.setFirstName(newName);
        entity.setFullName(fullName);
        service.saveEntity(entity);

        LocalResidentEntity entityAfter = service.getEntityById(id).get();

        Assertions.assertEquals(newName, entityAfter.getFullName().getFirstName());
        Assertions.assertNotEquals(lmtBefore, entityAfter.getLastModifiedTime());
    }
}
