package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private String id;
    private String text;
    private boolean hidden;
}
