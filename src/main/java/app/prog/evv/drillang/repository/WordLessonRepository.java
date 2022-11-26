package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.WordLessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordLessonRepository extends JpaRepository<WordLessonEntity, Long> {

}
