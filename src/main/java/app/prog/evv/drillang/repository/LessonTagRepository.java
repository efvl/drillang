package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.LessonTagEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTagRepository extends BaseJpaRepository<LessonTagEntity, Long>{
}
