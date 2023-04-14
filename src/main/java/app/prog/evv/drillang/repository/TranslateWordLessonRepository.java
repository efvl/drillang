package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.lesson.TranslateWordLessonSearchRequest;
import app.prog.evv.drillang.dto.lesson.WordLessonProjection;
import app.prog.evv.drillang.dto.lesson.WordLessonSearchRequest;
import app.prog.evv.drillang.dto.wordCard.WordCardSearchRequest;
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
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TranslateWordLessonRepository extends BaseJpaRepository<TranslateWordLessonEntity, Long>, QuerydslPredicateExecutor<TranslateWordLessonEntity> {

    List<TranslateWordLessonEntity> findByWordLessonId(Long id);
    List<TranslateWordLessonEntity> findByWordLessonIdOrderByIdDesc(Long id);

    List<TranslateWordLessonEntity> findByWordLessonIdAndTranslateId(Long wlId, Long trId);

    @Modifying
    @Query("update TranslateWordLessonEntity twl set twl.correctAnswer = 0 where twl.wordLesson.id = :lessonId and twl.skip = false")
    void setLessonLearnAgain(@Param("lessonId") Long lessonId);

    @Query("select twl.wordLesson.id AS lessonId, COUNT(twl.translate) AS trCount " +
            " from TranslateWordLessonEntity twl " +
            " where twl.wordLesson.id in (:ids) " +
            " group by twl.wordLesson.id ")
    List<WordLessonProjection> getTranslatesCount(@Param("ids") List<Long> ids);

    default Page<TranslateWordLessonEntity> search(TranslateWordLessonSearchRequest searchRequest, Pageable pageable){

        QTranslateWordLessonEntity translateLessonEntity = QTranslateWordLessonEntity.translateWordLessonEntity;

        JPAQuery<TranslateWordLessonEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getWordLessonId())) {
                whereCause.and(translateLessonEntity.wordLesson.id.eq(searchRequest.getWordLessonId()));
            }
        }

        query.from(translateLessonEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(translateLessonEntity.id.desc());

        QueryResults<TranslateWordLessonEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
