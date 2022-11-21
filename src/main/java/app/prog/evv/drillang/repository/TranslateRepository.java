package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.translate.Translate;
import app.prog.evv.drillang.entity.TranslateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateRepository extends JpaRepository<TranslateEntity, Long> {


}
