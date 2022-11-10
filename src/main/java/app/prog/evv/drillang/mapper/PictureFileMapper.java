package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.PictureFileDto;
import app.prog.evv.drillang.entity.PictureFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PictureFileMapper {

    PictureFileDto toDto(PictureFile entity);

    PictureFile toEntity(PictureFileDto dto);

}
