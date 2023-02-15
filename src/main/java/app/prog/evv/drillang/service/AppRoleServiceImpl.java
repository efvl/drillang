package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.Role;
import app.prog.evv.drillang.dto.user.RoleSearchRequest;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.AppRoleMapper;
import app.prog.evv.drillang.repository.AppRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRoleServiceImpl.class);
    private final AppRoleRepository repository;
    private final AppRoleMapper mapper;

    public AppRoleServiceImpl(AppRoleRepository repository, AppRoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Role getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("app role entity not found by (id=%d)", id)));
    }

    @Override
    public void deleteRoleById(Long id) {
        repository.deleteById(id);
        LOGGER.info("app role deleted id=" + id);
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public Role createRole(Role user) {
        return null;
    }

    @Override
    public List<Role> searchTags(RoleSearchRequest searchRequest) {
        return null;
    }
}
