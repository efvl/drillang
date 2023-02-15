package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.AppUser;
import app.prog.evv.drillang.dto.user.FormLoginRequest;
import app.prog.evv.drillang.dto.user.UserSearchRequest;

import java.util.List;

public interface AppUserService {

    AppUser getById(Long id);

    void deleteUserById(Long id);

    AppUser updateUser(AppUser user);

    AppUser createUser(AppUser user);

    List<AppUser> searchUsers(UserSearchRequest searchRequest);

    AppUser login(FormLoginRequest request);

    AppUser register(FormLoginRequest request);
}
