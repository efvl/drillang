package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.wordAudio.AudioFileDto;
import app.prog.evv.drillang.dto.wordAudio.AudioFileInfo;
import app.prog.evv.drillang.entity.AudioFileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AudioFileMapper {

    AudioFileDto toDto(AudioFileEntity entity);

    AudioFileInfo toAudioInfo(AudioFileEntity entity);

    AudioFileEntity toEntity(AudioFileDto dto);

}
