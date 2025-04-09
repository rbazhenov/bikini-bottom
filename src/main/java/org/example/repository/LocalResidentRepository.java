package org.example.repository;

import org.example.entity.resident.LocalResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LocalResidentRepository extends JpaRepository<LocalResidentEntity, String>,
        QuerydslPredicateExecutor<LocalResidentEntity> {
}
