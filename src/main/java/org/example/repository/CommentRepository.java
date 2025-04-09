package org.example.repository;

import org.example.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommentRepository extends JpaRepository<CommentEntity, String>,
        QuerydslPredicateExecutor<CommentEntity> {
}
