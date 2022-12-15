package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.lesson.WordLesson;
import app.prog.evv.drillang.dto.lesson.WordLessonSearchRequest;
import app.prog.evv.drillang.service.WordLessonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/word-lesson")
@CrossOrigin
public class WordLessonController {

    private final WordLessonService wordLessonService;

    public WordLessonController(WordLessonService wordLessonService) {
        this.wordLessonService = wordLessonService;
    }

    @Operation(description = "Getting the Word Lesson ")
    @GetMapping("/{id}")
    public ResponseEntity<WordLesson> getWordLesson(@PathVariable Long id){
        return ResponseEntity.ok(wordLessonService.findById(id));
    }

    @Operation(description = "Create new Word Lesson")
    @PostMapping
    public ResponseEntity<WordLesson> createWordLesson(@RequestBody WordLesson wordLesson){
        return ResponseEntity.ok(wordLessonService.createWordLesson(wordLesson)) ;
    }

    @Operation(description = "Update Word Lesson")
    @PutMapping
    public ResponseEntity<WordLesson> updateWordLesson(@RequestBody WordLesson wordLesson){
        return ResponseEntity.ok(wordLessonService.updateWordLesson(wordLesson));
    }

    @Operation(description = "Delete Word Lesson")
    @DeleteMapping("/{id}")
    public void deleteWordLesson(@PathVariable Long id){
        wordLessonService.deleteWordLessonById(id);
    }

    @Operation(description = "Search Word Lessons by searching params")
    @PostMapping("/search")
    public ResponseEntity<Page<WordLesson>> searchWordLessons(@RequestBody WordLessonSearchRequest searchRequest){
        return ResponseEntity.ok().body(wordLessonService.searchWordLessons(searchRequest));
    }

    @Operation(description = "Get all Lessons for selected fromLanguage")
    @GetMapping("/from-lang/{fromLangId}")
    public ResponseEntity<List<WordLesson>> getLessonByFromLang(@PathVariable(name = "fromLangId") Long fromLangId){
        return ResponseEntity.ok().body(wordLessonService.getLessonsByFromLanguage(fromLangId));
    }
}
