package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.PictureFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureFileRepository extends JpaRepository<PictureFile, Long> {

    List<PictureFile> findByChecksum(String checksum);


}
