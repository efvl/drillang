package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.testCard.TestCardDto;
import app.prog.evv.drillang.dto.testCard.TestCardSearchRequest;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import org.springframework.data.domain.Page;

public interface TestCardService {

    TestCardDto findById(Long id);

    TestCardDto createTestCard(TestCardDto testCardDto);

    TestCardDto updateTestCard(TestCardDto testCardDto);

    void deleteTestCardById(Long id);

    Page<TestCardDto> searchTestCards(TestCardSearchRequest request);

    Page<TestCardDto> searchCardsNotInLesson(TestCardTestLessonSearchRequest request);

}
