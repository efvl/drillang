package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.user.Role;
import app.prog.evv.drillang.entity.AppRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppRoleMapper {

    AppRoleEntity toEntity(Role dto);

    Role toDto(AppRoleEntity entity);

}
