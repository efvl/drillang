package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.translate.Translate;
import app.prog.evv.drillang.dto.translate.TranslateSearchRequest;
import app.prog.evv.drillang.service.TranslateService;
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

    @GetMapping("/id")
    ResponseEntity<Translate> getTranslateById(@PathVariable Long id){
        return ResponseEntity.ok(translateService.findById(id));
    }

    @PostMapping
    ResponseEntity<Translate> createTranslate(@RequestBody Translate translate){
        return ResponseEntity.ok(translateService.createTranslate(translate));
    }

    @PutMapping
    ResponseEntity<Translate> updateTranslate(@RequestBody Translate translate){
        return ResponseEntity.ok(translateService.updateTranslate(translate));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        translateService.deleteTranslateById(id);
    }

    @PostMapping("/search")
    ResponseEntity<Page<Translate>> searchTranslate(TranslateSearchRequest searchRequest){
        return  ResponseEntity.ok(translateService.searchTranslate(searchRequest));
    }

}
