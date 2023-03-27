package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.testLesson.TestLessonDto;
import app.prog.evv.drillang.dto.testLesson.TestLessonSearchRequest;
import app.prog.evv.drillang.service.TestLessonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/test-lesson")
@CrossOrigin
public class TestLessonController {

    private final TestLessonService testLessonService;

    public TestLessonController(TestLessonService testLessonService) {
        this.testLessonService = testLessonService;
    }

    @Operation(description = "Getting the Test Lesson ")
    @GetMapping("/{id}")
    public ResponseEntity<TestLessonDto> getWordLesson(@PathVariable Long id){
        return ResponseEntity.ok(testLessonService.findById(id));
    }

    @Operation(description = "Create new Test Lesson")
    @PostMapping
    public ResponseEntity<TestLessonDto> createWordLesson(@RequestBody TestLessonDto testLesson){
        return ResponseEntity.ok(testLessonService.createTestLesson(testLesson)) ;
    }

    @Operation(description = "Update Test Lesson")
    @PutMapping
    public ResponseEntity<TestLessonDto> updateWordLesson(@RequestBody TestLessonDto testLesson){
        return ResponseEntity.ok(testLessonService.updateTestLesson(testLesson));
    }

    @Operation(description = "Delete Test Lesson")
    @DeleteMapping("/{id}")
    public void deleteWordLesson(@PathVariable Long id){
        testLessonService.deleteTestLessonById(id);
    }

    @Operation(description = "Search Test Lessons by searching params")
    @PostMapping("/search")
    public ResponseEntity<Page<TestLessonDto>> searchWordLessons(@RequestBody TestLessonSearchRequest searchRequest){
        return ResponseEntity.ok().body(testLessonService.searchTestLessons(searchRequest));
    }

}
