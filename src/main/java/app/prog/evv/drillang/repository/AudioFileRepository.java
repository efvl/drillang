package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.AudioFileEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioFileRepository extends BaseJpaRepository<AudioFileEntity, Long> {

    Optional<AudioFileEntity> findByChecksum(String checksum);

}
