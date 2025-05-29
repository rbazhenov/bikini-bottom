package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.resident.ResidentEntity;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity extends BaseEntity {

    @NotNull
    private String text;

    private boolean hidden;

    @JsonIgnore
    @OneToOne(mappedBy = "comment")
    private ResidentEntity resident;

}
