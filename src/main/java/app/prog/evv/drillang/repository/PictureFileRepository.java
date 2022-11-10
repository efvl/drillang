package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.PictureFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureFileRepository extends JpaRepository<PictureFile, Long> {

}
