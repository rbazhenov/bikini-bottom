package org.example.model.pet;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.FullName;

@Getter
@Setter
public abstract class Pet {
    private String id;
    private FullName fullName;
    private int age;
}
