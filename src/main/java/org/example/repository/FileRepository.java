package org.example.repository;

import org.example.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FileRepository extends JpaRepository<FileEntity, String>, QuerydslPredicateExecutor<FileEntity> {
}
