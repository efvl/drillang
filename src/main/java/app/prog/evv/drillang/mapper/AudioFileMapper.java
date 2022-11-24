package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.AudioFileDto;
import app.prog.evv.drillang.entity.AudioFileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AudioFileMapper {

    AudioFileDto toDto(AudioFileEntity entity);

    AudioFileEntity toEntity(AudioFileDto dto);

}
