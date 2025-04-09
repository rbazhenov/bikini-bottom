package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.BaseEntity;
import org.example.model.file.FileType;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class FileEntity extends BaseEntity {

    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FileType type;

    private String externalId;

    private boolean deleted;
}
