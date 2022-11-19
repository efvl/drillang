package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.wordCard.WordCardDto;
import app.prog.evv.drillang.dto.wordCard.WordCardInfo;
import app.prog.evv.drillang.entity.WordCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LangMapper.class, PictureFileMapper.class, AudioFileMapper.class})
public interface WordCardMapper {

    WordCardDto toDto(WordCardEntity entity);

    WordCardInfo toWordCardInfo(WordCardEntity entity);

    WordCardEntity toEntity(WordCardDto dto);

}
