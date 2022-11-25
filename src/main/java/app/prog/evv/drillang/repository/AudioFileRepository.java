package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.AudioFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioFileRepository extends JpaRepository<AudioFileEntity, Long> {

    Optional<AudioFileEntity> findByChecksum(String checksum);

}
