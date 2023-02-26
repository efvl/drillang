package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.user.RefreshToken;
import app.prog.evv.drillang.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AppUserMapper.class})
public interface RefreshTokenMapper {

    @Mapping(target = "token", source = "refreshToken")
    RefreshToken toDto(RefreshTokenEntity entity);

    @Mapping(target = "refreshToken", source = "token")
    RefreshTokenEntity toEntity(RefreshToken dto);

}
