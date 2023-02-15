package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.RegisterRequest;
import app.prog.evv.drillang.dto.user.Role;
import app.prog.evv.drillang.entity.AppRoleEntity;
import app.prog.evv.drillang.entity.AppUserEntity;
import app.prog.evv.drillang.mapper.AppUserMapper;
import app.prog.evv.drillang.repository.AppRoleRepository;
import app.prog.evv.drillang.repository.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final AppUserMapper userMapper;

    public UserService(AppUserRepository userRepository, AppRoleRepository roleRepository, AppUserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserEntity appUser = userRepository.findByLogin(username);
        if(appUser == null){
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new User(appUser.getLogin(), appUser.getPwd(), mapRolesEntityToAuthorities(appUser.getRoles()));
    }

    public UserDetails registerUser(RegisterRequest request) {
        AppUserEntity existingEntity = userRepository.findByLogin(request.getLogin());
        if(existingEntity != null){
            throw new IllegalArgumentException(String.format("User '%s' already exists", request.getLogin()));
        }
        AppUserEntity newUser = userMapper.fromRegistrationRequest(request);
        AppRoleEntity defaultRole = roleRepository.findByName("DEFAULT");
        newUser.setRoles(Set.of(defaultRole));
        AppUserEntity registeredUser = userRepository.save(newUser);
        return new User(registeredUser.getLogin(), registeredUser.getPwd(), mapRolesEntityToAuthorities(registeredUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesEntityToAuthorities(Collection<AppRoleEntity> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
