package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.testLesson.TestLessonDto;
import app.prog.evv.drillang.dto.testLesson.TestLessonSearchRequest;
import org.springframework.data.domain.Page;

public interface TestLessonService {

    TestLessonDto findById(Long id);

    TestLessonDto createTestLesson(TestLessonDto testLesson);

    TestLessonDto updateTestLesson(TestLessonDto testLesson);

    void deleteTestLessonById(Long id);

    Page<TestLessonDto> searchTestLessons(TestLessonSearchRequest request);

}
