package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.entity.TranslateWordLessonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { TranslateMapper.class, WordLessonMapper.class })
public interface TranslateWordLessonMapper {

    TranslateWordLesson toDto(TranslateWordLessonEntity entity);

    TranslateWordLessonEntity toEntity(TranslateWordLesson dto);

}
