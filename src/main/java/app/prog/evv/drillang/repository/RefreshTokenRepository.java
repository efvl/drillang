package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.entity.RefreshTokenEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefreshTokenRepository extends BaseJpaRepository<RefreshTokenEntity, Long> {

    List<RefreshTokenEntity> findByUserId(Long userId);

    List<RefreshTokenEntity> findByToken(String token);

    void deleteByUserId(Long userId);

}
