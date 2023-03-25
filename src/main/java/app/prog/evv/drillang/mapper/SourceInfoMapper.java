package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.source.SourceInfoDto;
import app.prog.evv.drillang.entity.SourceInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceInfoMapper {

    SourceInfoDto toDto(SourceInfoEntity entity);

    SourceInfoEntity toEntity(SourceInfoDto dto);

}
