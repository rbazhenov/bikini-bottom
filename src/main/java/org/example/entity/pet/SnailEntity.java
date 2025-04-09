package org.example.entity.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.model.pet.MucusLevel;

/**
 * Улитка.
 */
@Entity
@Getter
@Setter
@Table(name = "pet_snail")
public class SnailEntity extends PetEntity {

    @Enumerated(EnumType.STRING)
    private MucusLevel mucusLevel;
}
