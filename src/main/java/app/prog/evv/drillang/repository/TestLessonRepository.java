package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.TestLessonEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TestLessonRepository extends BaseJpaRepository<TestLessonEntity, Long> {

}
