package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.lessonTag.LessonTagDto;
import app.prog.evv.drillang.entity.LessonTagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonTagMapper {

    LessonTagEntity toEntity(LessonTagDto dto);

    LessonTagDto toDto(LessonTagEntity entity);

}
