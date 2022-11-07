package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.LanguageDto;
import app.prog.evv.drillang.entity.Language;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LangMapper {

    LanguageDto toDto(Language entity);

    Language toEntity(LanguageDto dto);

}
