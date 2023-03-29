package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import app.prog.evv.drillang.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    default Page<TestCardTestLessonEntity> search(TestCardTestLessonSearchRequest searchRequest, Pageable pageable){

        QTestCardTestLessonEntity testCardTestLessonEntity = QTestCardTestLessonEntity.testCardTestLessonEntity;

        JPAQuery<TestCardTestLessonEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getName())) {
                whereCause.and(testCardTestLessonEntity.testLesson.name.like("%" + searchRequest.getName() + "%"));
            }
        }

        query.from(testCardTestLessonEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(testCardTestLessonEntity.id.desc());

        QueryResults<TestCardTestLessonEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

}
