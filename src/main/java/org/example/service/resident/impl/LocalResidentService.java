package org.example.service.resident.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.model.resident.LocalResident;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LocalResidentService extends ResidentServiceImpl<LocalResident> {

    @Override
    public Class<LocalResident> getResidentType() {
        return LocalResident.class;
    }

    @Override
    public Optional<LocalResident> save(LocalResident resident) {
        return saveBase(resident)
                .map(LocalResident.class::cast);
    }
}
