package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.LanguageDto;
import app.prog.evv.drillang.dto.LanguageSearchRequest;
import app.prog.evv.drillang.service.LangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/lang")
public class LangController {

    private final LangService langService;


    public LangController(LangService langService) {
        this.langService = langService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> getLanguage(@PathVariable Long id){
        return ResponseEntity.ok(langService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LanguageDto> createLanguage(@RequestBody LanguageDto languageDto){
        return ResponseEntity.ok(langService.createLanguage(languageDto)) ;
    }

    @PutMapping
    public ResponseEntity<LanguageDto> updateLanguage(@RequestBody LanguageDto languageDto){
        return ResponseEntity.ok(langService.updateLanguage(languageDto));
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Long id){
        langService.deleteLanguageById(id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<LanguageDto>> searchLanguages(@RequestBody LanguageSearchRequest searchRequest){
        return ResponseEntity.ok().body(langService.search(searchRequest));
    }

}
