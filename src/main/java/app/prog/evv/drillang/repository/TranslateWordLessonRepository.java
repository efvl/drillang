package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.TranslateWordLessonEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslateWordLessonRepository extends BaseJpaRepository<TranslateWordLessonEntity, Long> {

    List<TranslateWordLessonEntity> findByWordLessonId(Long id);

    List<TranslateWordLessonEntity> findByWordLessonIdAndTranslateId(Long wlId, Long trId);

}
