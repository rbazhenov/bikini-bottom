package org.example.model.resident;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.FullName;
import org.example.model.Comment;
import org.example.model.dictionary.Region;
import org.example.model.file.File;

import java.util.List;

@Getter
@Setter
public abstract class Resident {
    private String id;
    private FullName fullName;
    private Comment comment;
    private Region region;
    private List<File> files;
    private Boolean validated;
    private String validationId;
}
