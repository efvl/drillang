package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.testLesson.TestLessonDto;
import app.prog.evv.drillang.entity.TestLessonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LessonTagMapper.class})
public interface TestLessonMapper {

    TestLessonDto toDto(TestLessonEntity entity);

    TestLessonEntity toEntity(TestLessonDto dto);

}
