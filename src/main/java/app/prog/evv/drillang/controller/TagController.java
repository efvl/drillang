package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.TagSearchRequest;
import app.prog.evv.drillang.dto.tag.Tag;
import app.prog.evv.drillang.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/v1/tag")
@CrossOrigin
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Operation(description = "Getting tag type")
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable Long id){
        return ResponseEntity.ok().body(tagService.getById(id));
    }

    @Operation(description = "Create new Tag")
    @PostMapping()
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        return ResponseEntity.ok().body(tagService.createTag(tag));
    }

    @Operation(description = "Update Tag")
    @PutMapping
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag){
        return ResponseEntity.ok().body(tagService.updateTag(tag));
    }

    @Operation(description = "Delete Tag by id")
    @DeleteMapping(path = "/{id}")
    public void deleteTag(@PathVariable Long id){
        tagService.deleteTagById(id);
    }

    @Operation(description = "Search Tags by conditions")
    @PostMapping(path = "/search")
    public ResponseEntity<List<Tag>> searchTags(@RequestBody TagSearchRequest searchRequest){
        return ResponseEntity.ok().body(tagService.searchTags(searchRequest));
    }

}
