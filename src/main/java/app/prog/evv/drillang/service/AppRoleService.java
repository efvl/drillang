package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.Role;
import app.prog.evv.drillang.dto.user.RoleSearchRequest;

import java.util.List;

public interface AppRoleService {

    Role getById(Long id);

    void deleteRoleById(Long id);

    Role updateRole(Role role);

    Role createRole(Role user);

    List<Role> searchTags(RoleSearchRequest searchRequest);

}
