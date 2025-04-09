package org.example.entity.resident;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "resident_mystic")
public class MysticResidentEntity extends ResidentEntity {

    private int appearancePerYear;
}
