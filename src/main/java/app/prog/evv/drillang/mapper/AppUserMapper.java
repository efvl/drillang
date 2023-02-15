package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.user.AppUser;
import app.prog.evv.drillang.dto.user.RegisterRequest;
import app.prog.evv.drillang.entity.AppUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AppRoleMapper.class})
public interface AppUserMapper {

    AppUserEntity toEntity(AppUser dto);

    AppUser toDto(AppUserEntity entity);

    @Mapping(target = "pwd", source = "password")
    AppUserEntity fromRegistrationRequest(RegisterRequest request);

}
