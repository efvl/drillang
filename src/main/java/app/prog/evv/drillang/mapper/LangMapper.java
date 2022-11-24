package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import app.prog.evv.drillang.entity.LanguageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LangMapper {

    LanguageDto toDto(LanguageEntity entity);

    LanguageEntity toEntity(LanguageDto dto);

}
