package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.lesson.TranslateWLessonInfo;
import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.entity.TranslateWordLessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { TranslateMapper.class, WordLessonMapper.class })
public interface TranslateWordLessonMapper {

    TranslateWordLesson toDto(TranslateWordLessonEntity entity);

    @Mapping(target = "translateId", source = "translate.id")
    @Mapping(target = "lessonId", source = "wordLesson.id")
    @Mapping(target = "word1", source = "translate.word1.word")
    @Mapping(target = "word2", source = "translate.word2.word")
    TranslateWLessonInfo toInfoDto(TranslateWordLessonEntity entity);

    TranslateWordLessonEntity toEntity(TranslateWordLesson dto);

}
