package app.prog.evv.drillang.repository;

import app.prog.evv.drillang.dto.user.Role;
import app.prog.evv.drillang.entity.AppRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends BaseJpaRepository<AppRoleEntity, Long> {

    AppRoleEntity findByName(String name);

}
