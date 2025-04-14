package org.example.service.resident;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.resident.QResidentEntity;
import org.example.entity.resident.ResidentEntity;
import org.example.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentEntityService {

    private final ResidentRepository repository;
    private final JPAQueryFactory queryFactory;

    @Setter(onMethod = @__(@Autowired))
    private ResidentListService listService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<ResidentEntity> getLockedById(String id) {
        return getEntityById(id, true);
    }

    public Optional<ResidentEntity> getEntityById(String id) {
        return getEntityById(id, false);
    }

    private Optional<ResidentEntity> getEntityById(String id, boolean withPessimisticLock) {
        QResidentEntity entity = QResidentEntity.residentEntity;

        BooleanExpression expression = entity.id.eq(id);
        JPAQuery<ResidentEntity> query = queryFactory
                .select(entity)
                .from(entity)
                .where(expression);

        if (withPessimisticLock) {
            query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        }

        return Optional.ofNullable(query.fetchOne());
    }

    public ResidentEntity save(ResidentEntity entity) {
        return repository.save(entity);
    }

    public void delete(ResidentEntity entity) {
        repository.delete(entity);
    }

    public List<ResidentEntity> getResidents() {
        return null;
        //CustomsApplicationEntityService.getApplications
    }

}
