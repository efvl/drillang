package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.WordCardDto;
import app.prog.evv.drillang.entity.WordCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LangMapper.class, PictureFileMapper.class, AudioFileMapper.class})
public interface WordCardMapper {

    WordCardDto toDto(WordCard entity);

    WordCard toEntity(WordCardDto dto);

}
