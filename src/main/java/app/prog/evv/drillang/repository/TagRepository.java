package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.TagEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends BaseJpaRepository<TagEntity, Long>{

}
