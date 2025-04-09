package org.example.repository;

import org.example.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ClubRepository extends JpaRepository<ClubEntity, String>, QuerydslPredicateExecutor<ClubEntity> {
}
