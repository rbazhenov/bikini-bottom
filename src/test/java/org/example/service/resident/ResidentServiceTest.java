package org.example.service.resident;

import org.example.SpringApplicationTest;
import org.example.entity.FullName;
import org.example.entity.resident.ResidentEntity;
import org.example.model.resident.LocalResident;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

@SpringApplicationTest
class ResidentServiceTest {

    @Autowired
    private ResidentServiceResolver resolver;
    @Autowired
    private ResidentEntityService entityService;

    @Test
    void updateLastModifiedTest() {
        FullName fullName = new FullName();
        fullName.setFirstName("local");
        fullName.setLastName("ship");
        LocalResident localResident = new LocalResident();
        localResident.setFullName(fullName);

        ResidentService<LocalResident> service = resolver.getByResident(localResident);
        String id = service.save(localResident).get().getId();

        ResidentEntity entity = entityService.getEntityById(id).get();
        ZonedDateTime lmtBefore = entity.getLastModifiedTime();

        String newName = "newName";
        fullName.setFirstName(newName);
        entity.setFullName(fullName);
        entityService.save(entity);

        ResidentEntity entityAfter = entityService.getEntityById(id).get();

        Assertions.assertEquals(newName, entityAfter.getFullName().getFirstName());
        Assertions.assertNotEquals(lmtBefore, entityAfter.getLastModifiedTime());
    }
}
