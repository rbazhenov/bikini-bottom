package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity extends BaseEntity {

    @NotNull
    private String text;

    private boolean hidden;

}
