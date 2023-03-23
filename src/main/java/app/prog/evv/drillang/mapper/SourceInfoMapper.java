package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.source.SourceInfo;
import app.prog.evv.drillang.entity.SourceInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceInfoMapper {

    SourceInfo toDto(SourceInfoEntity entity);

    SourceInfoEntity toEntity(SourceInfo dto);

}
