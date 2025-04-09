package org.example.repository;

import org.example.entity.pet.SnailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SnailRepository extends JpaRepository<SnailEntity, String>, QuerydslPredicateExecutor<SnailEntity> {
}
