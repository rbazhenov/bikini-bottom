package org.example.model.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class File {
    private String id;
    private String name;
    private FileType type;
    private String externalId;
    private boolean deleted;
}
