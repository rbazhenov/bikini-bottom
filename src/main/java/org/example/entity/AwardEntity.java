package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.model.award.Importance;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "award")
public class AwardEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    @Enumerated(EnumType.STRING)
    private Importance importance;
}
