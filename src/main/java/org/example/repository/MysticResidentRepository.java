package org.example.repository;

import org.example.entity.resident.MysticResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MysticResidentRepository extends JpaRepository<MysticResidentEntity, String>,
        QuerydslPredicateExecutor<MysticResidentEntity> {
}
