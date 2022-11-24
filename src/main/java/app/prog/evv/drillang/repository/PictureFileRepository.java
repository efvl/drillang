package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.PictureFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureFileRepository extends JpaRepository<PictureFileEntity, Long> {

    List<PictureFileEntity> findByChecksum(String checksum);


}
