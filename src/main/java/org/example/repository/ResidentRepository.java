package org.example.repository;

import org.example.entity.resident.ResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ResidentRepository extends JpaRepository<ResidentEntity, String>,
        QuerydslPredicateExecutor<ResidentEntity> {
}
