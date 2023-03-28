package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.testLesson.TCardTLessonInfo;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonDto;
import app.prog.evv.drillang.entity.TestCardTestLessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { TestCardMapper.class, TestLessonMapper.class })
public interface TestCardTestLessonMapper {

    TestCardTestLessonDto toDto(TestCardTestLessonEntity entity);

    @Mapping(target = "testCardId", source = "testCard.id")
    @Mapping(target = "lessonId", source = "testLesson.id")
    @Mapping(target = "pictureId", source = "testCard.pictureId")
    @Mapping(target = "question", source = "testCard.question")
    @Mapping(target = "answer", source = "testCard.answer")
    TCardTLessonInfo toInfoDto(TestCardTestLessonEntity entity);

    TestCardTestLessonEntity toEntity(TestCardTestLessonDto dto);

}
