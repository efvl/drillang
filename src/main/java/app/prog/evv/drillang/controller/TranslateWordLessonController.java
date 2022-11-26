package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.dto.lesson.TranslateWordLessonSearchRequest;
import app.prog.evv.drillang.service.TranslateWordLessonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/translate-wlesson")
@CrossOrigin
public class TranslateWordLessonController {

    private final TranslateWordLessonService translateWordLessonService;

    public TranslateWordLessonController(TranslateWordLessonService translateWordLessonService) {
        this.translateWordLessonService = translateWordLessonService;
    }

    @Operation(description = "Getting the Translate Word Lesson ")
    @GetMapping("/{id}")
    public ResponseEntity<TranslateWordLesson> getTranslateWordLesson(@PathVariable Long id){
        return ResponseEntity.ok(translateWordLessonService.findById(id));
    }

    @Operation(description = "Create new Translate Word Lesson")
    @PostMapping
    public ResponseEntity<TranslateWordLesson> createTranslateWordLesson(@RequestBody TranslateWordLesson translateWLesson){
        return ResponseEntity.ok(translateWordLessonService.createTranslateWordLesson(translateWLesson)) ;
    }

    @Operation(description = "Update Translate Word Lesson")
    @PutMapping
    public ResponseEntity<TranslateWordLesson> updateTranslateWordLesson(@RequestBody TranslateWordLesson translateWLesson){
        return ResponseEntity.ok(translateWordLessonService.updateTranslateWordLesson(translateWLesson));
    }

    @Operation(description = "Delete Translate Word Lesson")
    @DeleteMapping("/{id}")
    public void deleteTranslateWordLesson(@PathVariable Long id){
        translateWordLessonService.deleteTranslateWordLessonById(id);
    }

    @Operation(description = "Search Translate Word Lessons by searching params")
    @PostMapping("/search")
    public ResponseEntity<Page<TranslateWordLesson>> searchTranslateWLessons(@RequestBody TranslateWordLessonSearchRequest searchRequest){
        return ResponseEntity.ok().body(translateWordLessonService.searchTranslateWordLessons(searchRequest));
    }
}
