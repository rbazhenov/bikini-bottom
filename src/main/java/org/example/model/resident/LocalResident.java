package org.example.model.resident;

import lombok.Getter;
import lombok.Setter;
import org.example.model.award.Award;

import java.util.List;

@Getter
@Setter
public class LocalResident extends Resident {
    private Club club;
    private List<Award> awards;
}
