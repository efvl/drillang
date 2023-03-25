package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.TestCardSourceEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCardSourceRepository extends BaseJpaRepository<TestCardSourceEntity, Long>,
        QuerydslPredicateExecutor<TestCardSourceEntity> {



}
