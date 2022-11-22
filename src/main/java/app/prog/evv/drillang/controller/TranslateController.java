package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.translate.Translate;
import app.prog.evv.drillang.dto.translate.TranslateSearchRequest;
import app.prog.evv.drillang.service.TranslateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/translate")
@CrossOrigin
public class TranslateController {

    private final TranslateService translateService;


    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @Operation(description = "Get Translation object by id")
    @GetMapping("/{id}")
    ResponseEntity<Translate> getTranslateById(@PathVariable Long id){
        return ResponseEntity.ok(translateService.findById(id));
    }

    @Operation(description = "Create new Translation")
    @PostMapping
    ResponseEntity<Translate> createTranslate(@RequestBody Translate translate){
        return ResponseEntity.ok(translateService.createTranslate(translate));
    }

    @Operation(description = "Update Translation")
    @PutMapping
    ResponseEntity<Translate> updateTranslate(@RequestBody Translate translate){
        return ResponseEntity.ok(translateService.updateTranslate(translate));
    }

    @Operation(description = "Delete Translation by id")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        translateService.deleteTranslateById(id);
    }

    @Operation(description = "Search Translation by searching params")
    @PostMapping("/search")
    ResponseEntity<Page<Translate>> searchTranslate(@RequestBody TranslateSearchRequest searchRequest){
        return  ResponseEntity.ok(translateService.searchTranslate(searchRequest));
    }

}
