package org.example.service.resident;

import org.example.model.resident.Resident;

import java.util.Optional;

public interface ResidentService<T extends Resident> {

    Class<T> getResidentType();

    Optional<T> save(T resident);
}
