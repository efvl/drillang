package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.lesson.WordLesson;
import app.prog.evv.drillang.entity.WordLessonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { LangMapper.class })
public interface WordLessonMapper {

    WordLesson toDto(WordLessonEntity entity);

    WordLessonEntity toEntity(WordLesson dto);

}
