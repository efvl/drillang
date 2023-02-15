package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.user.AppUser;
import app.prog.evv.drillang.entity.AppUserEntity;
import app.prog.evv.drillang.entity.TagEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends BaseJpaRepository<AppUserEntity, Long> {

    AppUserEntity findByLogin(String username);

}
