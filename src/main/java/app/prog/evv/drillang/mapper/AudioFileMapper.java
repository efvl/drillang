package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.AudioFileDto;
import app.prog.evv.drillang.entity.AudioFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AudioFileMapper {

    AudioFileDto toDto(AudioFile entity);

    AudioFile toEntity(AudioFileDto dto);

}
