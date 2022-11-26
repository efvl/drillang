package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.TranslateWordLessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateWordLessonRepository extends JpaRepository<TranslateWordLessonEntity, Long> {

}
