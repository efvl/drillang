package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.WordCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordCardRepository extends JpaRepository<WordCardEntity, Long> {

}
