package org.example.repository;

import org.example.entity.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AwardRepository extends JpaRepository<AwardEntity, String>, QuerydslPredicateExecutor<AwardEntity> {
}
