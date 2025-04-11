package org.example.model.award;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Award {
    private String id;
    private String name;
    private Importance importance;
}
