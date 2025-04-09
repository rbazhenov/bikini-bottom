package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.BaseEntity;
import org.example.entity.resident.LocalResidentEntity;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "club")
public class ClubEntity extends BaseEntity {

    private String name;

    @OneToMany
    @JoinColumn(name = "partner_employee_id")
    private List<LocalResidentEntity> members;

    //спортсмены, рестораны, наука
}
