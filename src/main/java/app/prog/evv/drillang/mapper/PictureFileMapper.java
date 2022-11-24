package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.wordPicture.PictureFileDto;
import app.prog.evv.drillang.dto.wordPicture.PictureFileInfo;
import app.prog.evv.drillang.entity.PictureFileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PictureFileMapper {

    PictureFileDto toDto(PictureFileEntity entity);

    PictureFileInfo toPictureInfo(PictureFileEntity entity);

    PictureFileEntity toEntity(PictureFileDto dto);

}
