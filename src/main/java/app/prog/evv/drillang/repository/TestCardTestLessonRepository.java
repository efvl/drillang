package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.TestCardTestLessonEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCardTestLessonRepository  extends BaseJpaRepository<TestCardTestLessonEntity, Long>, QuerydslPredicateExecutor<TestCardTestLessonEntity> {

    List<TestCardTestLessonEntity> findByTestLessonIdOrderByIdDesc(Long id);

    @Modifying
    @Query("update TestCardTestLessonEntity tctl set tctl.correctAnswer = 0 where tctl.testLesson.id = :lessonId and tctl.skip = false")
    void setLessonLearnAgain(@Param("lessonId") Long lessonId);

}
