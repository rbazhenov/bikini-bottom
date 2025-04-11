package org.example.service.resident.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.model.resident.MysticResident;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MysticResidentService extends ResidentServiceImpl<MysticResident> {

    @Override
    public Class<MysticResident> getResidentType() {
        return MysticResident.class;
    }

    @Override
    public Optional<MysticResident> save(MysticResident resident) {
        return saveBase(resident)
                .map(MysticResident.class::cast);
    }
}
