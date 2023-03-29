package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.testCard.TestCardDto;
import app.prog.evv.drillang.dto.testCard.TestCardSearchRequest;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import app.prog.evv.drillang.service.TestCardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/test-card")
@CrossOrigin
public class TestCardController {

    private final TestCardService testCardService;

    public TestCardController(TestCardService testCardService) {
        this.testCardService = testCardService;
    }

    @Operation(description = "Getting the Test Card ")
    @GetMapping("/{id}")
    public ResponseEntity<TestCardDto> getTestCard(@PathVariable Long id){
        return ResponseEntity.ok(testCardService.findById(id));
    }

    @Operation(description = "Create new Test Card")
    @PostMapping
    public ResponseEntity<TestCardDto> createTestCard(@RequestBody TestCardDto testCardDto){
        return ResponseEntity.ok(testCardService.createTestCard(testCardDto)) ;
    }

    @Operation(description = "Update Test Card")
    @PutMapping
    public ResponseEntity<TestCardDto> updateTestCard(@RequestBody TestCardDto testCardDto){
        return ResponseEntity.ok(testCardService.updateTestCard(testCardDto));
    }

    @Operation(description = "Delete Test Card")
    @DeleteMapping("/{id}")
    public void deleteTestCard(@PathVariable Long id){
        testCardService.deleteTestCardById(id);
    }

    @Operation(description = "Search Test Cards by searching params")
    @PostMapping("/search")
    public ResponseEntity<Page<TestCardDto>> searchTestCards(@RequestBody TestCardSearchRequest searchRequest){
        return ResponseEntity.ok().body(testCardService.searchTestCards(searchRequest));
    }

    @Operation(description = "Search Test Cards For Lesson by searching params")
    @PostMapping("/search/for-lesson")
    public ResponseEntity<Page<TestCardDto>> searchTestCardsForLesson(@RequestBody TestCardTestLessonSearchRequest searchRequest){
        return ResponseEntity.ok().body(testCardService.searchCardsNotInLesson(searchRequest));
    }

}
