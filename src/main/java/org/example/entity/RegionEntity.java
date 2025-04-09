package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "region")
public class RegionEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
}

