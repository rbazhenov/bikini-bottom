package org.example.entity.resident;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.BaseEntity;
import org.example.entity.CommentEntity;
import org.example.entity.FullName;
import org.example.entity.RegionEntity;
import org.example.entity.FileEntity;

import java.util.List;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "resident")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ResidentEntity extends BaseEntity {

    @NotNull
    @Embedded
    private FullName fullName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;

    @ManyToOne
    private RegionEntity region;

    @OneToMany
    @JoinTable(
            name = "resident_file_meta",
            joinColumns = @JoinColumn(name = "resident_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private List<FileEntity> photos;
}
