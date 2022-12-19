package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.lesson.TranslateWLessonInfo;
import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.dto.lesson.TranslateWordLessonSearchRequest;
import app.prog.evv.drillang.dto.lesson.WordLesson;
import app.prog.evv.drillang.service.TranslateWordLessonService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @Operation(description = "Learn again for Translate Word Lesson")
    @PutMapping("/again")
    public ResponseEntity<TranslateWordLesson> learnAgainTranslateWordLesson(@RequestBody TranslateWordLesson translateWLesson){
        return ResponseEntity.ok(translateWordLessonService.learnAgainTranslateWordLesson(translateWLesson));
    }

    @Operation(description = "Skip learning for Translate Word Lesson")
    @PutMapping("/skip")
    public ResponseEntity<TranslateWordLesson> skipTranslateWordLesson(@RequestBody TranslateWordLesson translateWLesson){
        return ResponseEntity.ok(translateWordLessonService.skipTranslateWordLesson(translateWLesson));
    }

    @Operation(description = "Update bunch of Translate Word Lessons")
    @PutMapping("/bunch")
    public ResponseEntity<List<TranslateWLessonInfo>> updateTranslateWordLessons(@RequestBody List<TranslateWordLesson> translateWLessons){
        return ResponseEntity.ok().body(translateWordLessonService.updateTranslateWordLessons(translateWLessons));
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

    @Operation(description = "Get All Translates For Lesson by id")
    @GetMapping("/by-lesson/{id}")
    public ResponseEntity<List<TranslateWLessonInfo>> searchTranslateForLesson(@PathVariable Long id){
        return ResponseEntity.ok().body(translateWordLessonService.getTranslatesForLesson(id));
    }

    @Operation(description = "set Lesson learn again")
    @PutMapping("/learn-again")
    public ResponseEntity<WordLesson> setLearnLessonAgain(@RequestBody WordLesson wordLesson) {
        if(ObjectUtils.isEmpty(wordLesson.getId())){
            throw new IllegalArgumentException("wordLesson id is empty");
        }
        translateWordLessonService.setLearnLessonAgain(wordLesson.getId());
        return ResponseEntity.ok().body(wordLesson);
    }
}
