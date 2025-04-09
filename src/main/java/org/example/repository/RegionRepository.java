package org.example.repository;

import org.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RegionRepository extends JpaRepository<RegionEntity, String>, QuerydslPredicateExecutor<RegionEntity> {
}
