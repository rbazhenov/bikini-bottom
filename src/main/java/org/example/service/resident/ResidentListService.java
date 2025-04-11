package org.example.service.resident;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResidentListService {

    @Getter
    @Value("${ui.residents.defaultLimit:10}")
    private Integer defaultLimit;

    private final ResidentRepository repository;
    private final JPAQueryFactory queryFactory;

    //todo rbs BaseApplicationListService
}
