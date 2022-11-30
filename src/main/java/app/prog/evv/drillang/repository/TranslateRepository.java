package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.TranslateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateRepository extends JpaRepository<TranslateEntity, Long> {

    @Query("select te from TranslateEntity te join te.word1 w1 " +
            " where w1.language.id = :langId " +
            "   and te.id not in (select distinct(twl.translate.id) from TranslateWordLessonEntity twl " +
            "                     where twl.wordLesson.id = :lessonId )")
    Page<TranslateEntity> searchTranslatesForLesson(@Param("langId") Long langId, @Param("lessonId") Long lessonId, Pageable pageable);

}
