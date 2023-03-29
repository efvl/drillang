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
    @Mapping(target = "pictureId", source = "translate.word1.pictureId")
    @Mapping(target = "word1", source = "translate.word1.word")
    @Mapping(target = "example1", source = "translate.word1.example")
    @Mapping(target = "audioId1", source = "translate.word1.audioId")
    @Mapping(target = "word2", source = "translate.word2.word")
    @Mapping(target = "example2", source = "translate.word2.example")
    @Mapping(target = "audioId2", source = "translate.word2.audioId")
    TranslateWLessonInfo toInfoDto(TranslateWordLessonEntity entity);

    TranslateWordLessonEntity toEntity(TranslateWordLesson dto);

}
