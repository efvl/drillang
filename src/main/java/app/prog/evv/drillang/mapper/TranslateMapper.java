package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.translate.Translate;
import app.prog.evv.drillang.entity.TranslateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WordCardMapper.class})
public interface TranslateMapper {

    Translate toDto(TranslateEntity entity);

    TranslateEntity toEntity(Translate dto);

}
