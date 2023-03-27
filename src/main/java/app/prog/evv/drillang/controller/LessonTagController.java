package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.lessonTag.LessonTagDto;
import app.prog.evv.drillang.dto.lessonTag.LessonTagSearchRequest;
import app.prog.evv.drillang.service.LessonTagService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/lesson-tag")
@CrossOrigin
public class LessonTagController {

    private final LessonTagService lessonTagService;

    public LessonTagController(LessonTagService lessonTagService) {
        this.lessonTagService = lessonTagService;
    }

    @Operation(description = "Getting lesson tag type")
    @GetMapping("/{id}")
    public ResponseEntity<LessonTagDto> getTag(@PathVariable Long id){
        return ResponseEntity.ok().body(lessonTagService.getById(id));
    }

    @Operation(description = "Create new LessonTag")
    @PostMapping()
    public ResponseEntity<LessonTagDto> createTag(@RequestBody LessonTagDto lessonTag){
        return ResponseEntity.ok().body(lessonTagService.createTag(lessonTag));
    }

    @Operation(description = "Update LessonTag")
    @PutMapping
    public ResponseEntity<LessonTagDto> updateTag(@RequestBody LessonTagDto lessonTag){
        return ResponseEntity.ok().body(lessonTagService.updateTag(lessonTag));
    }

    @Operation(description = "Delete LessonTag by id")
    @DeleteMapping(path = "/{id}")
    public void deleteTag(@PathVariable Long id){
        lessonTagService.deleteTagById(id);
    }

    @Operation(description = "Search LessonTags by conditions")
    @PostMapping(path = "/search")
    public ResponseEntity<List<LessonTagDto>> searchTags(@RequestBody LessonTagSearchRequest searchRequest){
        return ResponseEntity.ok().body(lessonTagService.searchTags(searchRequest));
    }

}
