package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.testLesson.TCardTLessonInfo;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonDto;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import app.prog.evv.drillang.dto.testLesson.TestLessonDto;
import app.prog.evv.drillang.service.TestCardTestLessonService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/tcard-tlesson")
@CrossOrigin
public class TestCardTestLessonController {

    private final TestCardTestLessonService tctLessonService;

    public TestCardTestLessonController(TestCardTestLessonService tctLessonService) {
        this.tctLessonService = tctLessonService;
    }

    @Operation(description = "Getting the Test Card Lesson ")
    @GetMapping("/{id}")
    public ResponseEntity<TestCardTestLessonDto> getTestCardTestLesson(@PathVariable Long id){
        return ResponseEntity.ok(tctLessonService.findById(id));
    }

    @Operation(description = "Create new Test Card Lesson")
    @PostMapping
    public ResponseEntity<TestCardTestLessonDto> createTestCardTestLesson(@RequestBody TestCardTestLessonDto testCardTestLesson){
        return ResponseEntity.ok(tctLessonService.createTCardTLesson(testCardTestLesson)) ;
    }

    @Operation(description = "Update Test Card Lesson")
    @PutMapping
    public ResponseEntity<TestCardTestLessonDto> updateTestCardTestLesson(@RequestBody TestCardTestLessonDto testCardTestLesson){
        return ResponseEntity.ok(tctLessonService.updateTCardTLesson(testCardTestLesson));
    }

    @Operation(description = "Learn again for Test Card Lesson")
    @PutMapping("/again")
    public ResponseEntity<TestCardTestLessonDto> learnAgainTestCardTestLesson(@RequestBody TestCardTestLessonDto testCardTestLesson){
        return ResponseEntity.ok(tctLessonService.learnAgainTCardTLesson(testCardTestLesson));
    }

    @Operation(description = "Skip learning for Test Card Lesson")
    @PutMapping("/skip")
    public ResponseEntity<TestCardTestLessonDto> skipTestCardTestLesson(@RequestBody TestCardTestLessonDto testCardTestLesson){
        return ResponseEntity.ok(tctLessonService.skipTCardTLesson(testCardTestLesson));
    }

    @Operation(description = "Update bunch of Test Card Lessons")
    @PutMapping("/bunch")
    public ResponseEntity<List<TCardTLessonInfo>> updateTestCardTestLessons(@RequestBody List<TestCardTestLessonDto> testCardTestLessons){
        return ResponseEntity.ok().body(tctLessonService.updateTCardTLessons(testCardTestLessons));
    }

    @Operation(description = "Delete Test Card Lesson")
    @DeleteMapping("/{id}")
    public void deleteTestCardTestLesson(@PathVariable Long id){
        tctLessonService.deleteTCardTLessonById(id);
    }

    @Operation(description = "Search Test CardLessons by searching params")
    @PostMapping("/search")
    public ResponseEntity<Page<TestCardTestLessonDto>> searchTestCardTestLessons(@RequestBody TestCardTestLessonSearchRequest searchRequest){
        return ResponseEntity.ok().body(tctLessonService.searchTCardTLessons(searchRequest));
    }

    @Operation(description = "Get All TestCards For Lesson by id")
    @GetMapping("/by-lesson/{id}")
    public ResponseEntity<List<TCardTLessonInfo>> searchTestCardsForLesson(@PathVariable Long id){
        return ResponseEntity.ok().body(tctLessonService.getTCardsForLesson(id));
    }

    @Operation(description = "set Lesson learn again")
    @PutMapping("/learn-again")
    public ResponseEntity<TestLessonDto> setLearnLessonAgain(@RequestBody TestLessonDto testLesson) {
        if(ObjectUtils.isEmpty(testLesson.getId())){
            throw new IllegalArgumentException("testLesson id is empty");
        }
        tctLessonService.setLearnLessonAgain(testLesson.getId());
        return ResponseEntity.ok().body(testLesson);
    }

}
