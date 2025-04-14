package org.example.repository;

import org.example.entity.FullName;
import org.example.entity.resident.LocalResidentEntity;
import org.example.model.Comment;
import org.example.model.award.Award;
import org.example.model.award.Importance;
import org.example.model.dictionary.Region;
import org.example.model.file.File;
import org.example.model.file.FileType;
import org.example.model.resident.Club;
import org.example.model.resident.LocalResident;
import org.example.service.resident.ResidentServiceResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@DataJpaTest
@ActiveProfiles("test")
class LocalResidentRepositoryTest {

    @Autowired
    private LocalResidentRepository localResidentRepository;

    @Autowired
    private ResidentServiceResolver serviceResolver;

    @Test
    void saveFullFilledDto() {
        Club club = new Club();
        club.setName("big teeth");

        Award award = new Award();
        award.setName("The best of the best");
        award.setImportance(Importance.LEGEND);
        Award award2 = new Award();
        award2.setName("The best cook");
        award2.setImportance(Importance.HIGH);

        FullName fullName = new FullName();
        fullName.setFirstName("SpongeBob");
        fullName.setLastName("SquarePants");

        Comment comment = new Comment();
        comment.setText("Yellow funny boy");
        comment.setHidden(false);

        Region region = new Region();
        region.setName("Center");

        File file = new File();
        file.setName("photo-1");
        file.setType(FileType.IMAGE);
        file.setExternalId("external_id_1");
        File file2 = new File();
        file2.setName("passport");
        file2.setType(FileType.PDF);
        file2.setExternalId("external_id_2");

        LocalResident localResident = new LocalResident();
        localResident.setClub(club);
        localResident.setAwards(Arrays.asList(award, award2));
        localResident.setFullName(fullName);
        localResident.setComment(comment);
        localResident.setRegion(region);
        localResident.setFiles(Arrays.asList(file, file2));

        String id = serviceResolver.getByResident(localResident).save(localResident).get().getId();

        LocalResidentEntity saved = localResidentRepository.findById(id).get();

        Assertions.assertEquals(saved.getClub().getName(), club.getName());
        Assertions.assertEquals(saved.getAwards().size(), 2);
        Assertions.assertEquals(saved.getFullName().getFirstName(), fullName.getFirstName());
        Assertions.assertEquals(saved.getComment().getText(), comment.getText());
        Assertions.assertEquals(saved.getRegion().getName(), region.getName());
        Assertions.assertEquals(saved.getFiles().size(), 2);
    }
}
