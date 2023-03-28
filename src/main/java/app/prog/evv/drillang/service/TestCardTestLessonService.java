package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.testLesson.TCardTLessonInfo;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonDto;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestCardTestLessonService {

    TestCardTestLessonDto findById(Long id);

    TestCardTestLessonDto createTCardTLesson(TestCardTestLessonDto tCardTLesson);

    TestCardTestLessonDto updateTCardTLesson(TestCardTestLessonDto tCardTLesson);

    void deleteTCardTLessonById(Long id);

    Page<TestCardTestLessonDto> searchTCardTLessons(TestCardTestLessonSearchRequest request);

    List<TCardTLessonInfo> getTCardsForLesson(Long lessonId);

    TestCardTestLessonDto learnAgainTCardTLesson(TestCardTestLessonDto tCardTLesson);

    TCardTLessonInfo skipTCardTLesson(TCardTLessonInfo tCardTLessonInfo);

    List<TCardTLessonInfo> updateTCardTLessons(List<TestCardTestLessonDto> tCardTLessons);

    void setLearnLessonAgain(Long lessonId);

}
