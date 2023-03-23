package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.source.SourceInfoSearchRequest;
import app.prog.evv.drillang.entity.QSourceInfoEntity;
import app.prog.evv.drillang.entity.SourceInfoEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceInfoRepository extends BaseJpaRepository<SourceInfoEntity, Long>, QuerydslPredicateExecutor<SourceInfoEntity> {

    default Page<SourceInfoEntity> search(SourceInfoSearchRequest searchRequest, Pageable pageable){

        QSourceInfoEntity sourceInfoEntity = QSourceInfoEntity.sourceInfoEntity;

        JPAQuery<SourceInfoEntity> query = new JPAQuery<>(getEm());

        BooleanBuilder whereCause = new BooleanBuilder();
        if(searchRequest != null){
            if(ObjectUtils.isNotEmpty(searchRequest.getName())) {
                whereCause.and(sourceInfoEntity.name.likeIgnoreCase(searchRequest.getName() + "%"));
            }
        }

        query.from(sourceInfoEntity).where(whereCause);
        if(pageable.isPaged()){
            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
        // default order
        query.orderBy(sourceInfoEntity.name.desc());

        QueryResults<SourceInfoEntity> results = query.fetchResults();
        // Convert back to a normal spring search result.
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

}
