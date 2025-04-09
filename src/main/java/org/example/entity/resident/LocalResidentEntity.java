package org.example.entity.resident;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.AwardEntity;
import org.example.entity.ClubEntity;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "resident_local")
public class LocalResidentEntity extends ResidentEntity {

    @ManyToOne
    private ClubEntity club;

    @ManyToMany
    @JoinTable(
            name = "resident_local_award",
            joinColumns = @JoinColumn(name = "resident_local_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    private List<AwardEntity> awards;

}
