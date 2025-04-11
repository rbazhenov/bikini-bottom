package org.example.service.resident;

import org.example.exception.ResidentNotFoundException;
import org.example.model.resident.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResidentServiceResolver {

    private final Map<Class<? extends Resident>, ResidentService<? extends Resident>> servicesMap = new HashMap<>();

    @Autowired
    public void registerServices(List<ResidentService<? extends Resident>> services) {
        for (ResidentService<? extends Resident> service : services) {
            Class<? extends Resident> type = service.getResidentType();
            if (type == null) {
                continue;
            }
            if (servicesMap.containsKey(type)) {
                throw new IllegalArgumentException("Second service for a type " + type);
            }
            servicesMap.put(type, service);
        }
    }

    public <T extends Resident> ResidentService<T> getByClass(Class<T> type) {
        if (!servicesMap.containsKey(type)) {
            return null;
        }
        return (ResidentService<T>) servicesMap.get(type);
    }

    public <T extends Resident> ResidentService<T> getByResident(T resident) {
        if (resident == null) {
            throw new ResidentNotFoundException("resident is null");
        }
        return (ResidentService<T>) getByClass(resident.getClass());
    }

    public Collection<ResidentService<? extends Resident>> getAll() {
        return servicesMap.values();
    }
}
