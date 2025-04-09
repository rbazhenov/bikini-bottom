package org.example.model.resident;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Club {
    private String name;
    private List<LocalResident> members;
}
