package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.AppUser;
import app.prog.evv.drillang.dto.user.FormLoginRequest;
import app.prog.evv.drillang.dto.user.UserSearchRequest;
import app.prog.evv.drillang.entity.AppUserEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.AppUserMapper;
import app.prog.evv.drillang.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImpl.class);
    private final AppUserRepository repository;
    private final AppUserMapper mapper;

    public AppUserServiceImpl(AppUserRepository repository, AppUserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AppUser getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("app user entity not found by (id=%d)", id)));
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
        LOGGER.info("app user deleted id=" + id);
    }

    @Override
    public AppUser updateUser(AppUser user) {
        return null;
    }

    @Override
    public AppUser createUser(AppUser user) {
        return null;
    }

    @Override
    public List<AppUser> searchUsers(UserSearchRequest searchRequest) {
        return null;
    }

    @Override
    public AppUser login(FormLoginRequest request) {
        AppUserEntity existing = repository.findByLogin(request.getLogin());
        if(existing == null){
            existing = new AppUserEntity();
        }
        return mapper.toDto(existing);
    }

    @Override
    public AppUser register(FormLoginRequest request) {
        AppUserEntity existing = repository.findByLogin(request.getLogin());
        if(existing == null){
            existing = new AppUserEntity();
        }
        return mapper.toDto(existing);
    }
}
