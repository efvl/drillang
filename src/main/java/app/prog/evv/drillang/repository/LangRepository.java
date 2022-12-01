package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.LanguageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LangRepository extends BaseJpaRepository<LanguageEntity, Long> {


}
