package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.testCard.TestCardSearchRequest;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import app.prog.evv.drillang.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface TestCardRepository extends BaseJpaRepository<TestCardEntity, Long>, QuerydslPredicateExecutor<TestCardEntity> {


    default Page<TestCardEntity> search(TestCardSearchRequest searchRequest, Pageable pageable){

        QTestCardEntity testCardEntity = QTestCardEntity.testCardEntity;

        JPAQuery<TestCardEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getQuestion())) {
                whereCause.and(testCardEntity.question.likeIgnoreCase("%" + searchRequest.getQuestion() + "%"));
            }
            if(!CollectionUtils.isEmpty(searchRequest.getTags())) {
                final List<Long> tagIds = searchRequest.getTags().stream()
                        .map(tag -> tag.getId())
                        .collect(Collectors.toList());
                whereCause.and(testCardEntity.tags.any().id.in(tagIds));
            }
        }

        query.from(testCardEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(testCardEntity.dateCreated.desc());

        QueryResults<TestCardEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    default Page<TestCardEntity> searchCardsNotInLesson(TestCardTestLessonSearchRequest searchRequest, Pageable pageable){

        QTestCardEntity testCardEntity = QTestCardEntity.testCardEntity;
        QTestCardTestLessonEntity tle = new QTestCardTestLessonEntity("tle");

        JPAQuery<TestCardEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getQuestion())) {
                whereCause.and(testCardEntity.question.likeIgnoreCase("%" + searchRequest.getQuestion() + "%"));
            }
            if(!CollectionUtils.isEmpty(searchRequest.getTags())) {
                final List<Long> tagIds = searchRequest.getTags().stream()
                        .map(tag -> tag.getId())
                        .collect(Collectors.toList());
                whereCause.and(testCardEntity.tags.any().id.in(tagIds));
            }
            if(ObjectUtils.isNotEmpty(searchRequest.getLessonId())) {
                whereCause.and(testCardEntity.id.notIn(
                                JPAExpressions.select(tle.testCard.id)
                                        .from(tle)
                                        .where(tle.testLesson.id.eq(searchRequest.getLessonId()))
                        )
                );
            }

        }

        query.from(testCardEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(testCardEntity.dateCreated.desc());

        QueryResults<TestCardEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

}
