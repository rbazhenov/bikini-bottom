package org.example.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Embeddable
public class FullName {
    @NotNull
    private String firstName;
    private String lastName;
}
