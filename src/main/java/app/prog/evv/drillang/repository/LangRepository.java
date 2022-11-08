package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LangRepository extends JpaRepository<Language, Long> {


}
