package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.wordCard.WordCardSearchRequest;
import app.prog.evv.drillang.entity.WordCardEntity;
import app.prog.evv.drillang.entity.QWordCardEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WordCardRepository extends BaseJpaRepository<WordCardEntity, Long>, QuerydslPredicateExecutor<WordCardEntity> {

    Page<WordCardEntity> findByLanguageId(PageRequest pageRequest, Long id);

    default Page<WordCardEntity> search(WordCardSearchRequest searchRequest, Pageable pageable){

        QWordCardEntity wordCardEntity = QWordCardEntity.wordCardEntity;

        JPAQuery<WordCardEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getLanguageId())) {
                whereCause.and(wordCardEntity.language.id.eq(searchRequest.getLanguageId()));
            }
            if(ObjectUtils.isNotEmpty(searchRequest.getWord())) {
                whereCause.and(wordCardEntity.word.likeIgnoreCase(searchRequest.getWord() + "%"));
            }
        }

        query.from(wordCardEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(wordCardEntity.dateCreated.desc());

        QueryResults<WordCardEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

}
