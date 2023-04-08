package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.translate.TranslateSearchRequest;
import app.prog.evv.drillang.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface TranslateRepository extends BaseJpaRepository<TranslateEntity, Long>, QuerydslPredicateExecutor<TranslateEntity> {

    @Query("select te from TranslateEntity te join te.word1 w1 " +
            " where w1.language.id = :langId " +
            "   and te.id not in (select distinct(twl.translate.id) from TranslateWordLessonEntity twl " +
            "                     where twl.wordLesson.id = :lessonId )")
    Page<TranslateEntity> searchTranslatesForLesson(@Param("langId") Long langId, @Param("lessonId") Long lessonId, Pageable pageable);

    default Page<TranslateEntity> searchTranslatesForLesson(TranslateSearchRequest searchRequest, Pageable pageable){

        QTranslateEntity translateEntity = QTranslateEntity.translateEntity;
        QTranslateWordLessonEntity twe = new QTranslateWordLessonEntity("twe");

        JPAQuery<TranslateEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getLanguageId())) {
                whereCause.and(translateEntity.word1.language.id.eq(searchRequest.getLanguageId()));
            }
            if(!CollectionUtils.isEmpty(searchRequest.getTags())) {
                final List<Long> tagIds = searchRequest.getTags().stream()
                        .map(tag -> tag.getId())
                        .collect(Collectors.toList());
                whereCause.and(translateEntity.word1.tags.any().id.in(tagIds));
            }
            if(ObjectUtils.isNotEmpty(searchRequest.getLessonId())) {
                whereCause.and(translateEntity.id.notIn(
                        JPAExpressions.select(twe.translate.id)
                                .from(twe)
                                .where(twe.wordLesson.id.eq(searchRequest.getLessonId()))
                        )
                );
            }

        }

        query.from(translateEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(translateEntity.word1.dateCreated.desc());

        QueryResults<TranslateEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    default Page<TranslateEntity> search(TranslateSearchRequest searchRequest, Pageable pageable){

        QTranslateEntity translateEntity = QTranslateEntity.translateEntity;

        JPAQuery<TranslateEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getLanguageId())) {
                whereCause.and(translateEntity.word1.language.id.eq(searchRequest.getLanguageId()));
            }
            if(ObjectUtils.isNotEmpty(searchRequest.getWord())) {
                whereCause.and(translateEntity.word1.word.likeIgnoreCase(searchRequest.getWord() + "%"));
            }
            if(!CollectionUtils.isEmpty(searchRequest.getTags())) {
                final List<Long> tagIds = searchRequest.getTags().stream()
                        .map(tag -> tag.getId())
                        .collect(Collectors.toList());
                whereCause.and(translateEntity.word1.tags.any().id.in(tagIds));
            }
        }

        query.from(translateEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(translateEntity.word1.dateCreated.desc());

        QueryResults<TranslateEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
