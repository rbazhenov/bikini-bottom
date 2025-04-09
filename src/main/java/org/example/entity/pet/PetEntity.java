package org.example.entity.pet;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.BaseEntity;
import org.example.entity.FullName;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@MappedSuperclass
public abstract class PetEntity extends BaseEntity {

    @NotNull
    private Integer age;

    @Embedded
    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "firstName",
                    column = @Column(name = "first_name")),
            @AttributeOverride(name = "lastName",
                    column = @Column(name = "last_name"))
    })
    private FullName fullName;
}


