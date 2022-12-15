package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.WordLessonEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordLessonRepository extends BaseJpaRepository<WordLessonEntity, Long> {

    List<WordLessonEntity> findByFromLanguageId(Long fromLangId);

}
