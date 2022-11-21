package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.wordCard.WordCardDto;
import app.prog.evv.drillang.dto.wordCard.WordCardSearchRequest;
import app.prog.evv.drillang.exception.ApiError;
import app.prog.evv.drillang.service.WordCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/v1/word-card")
@CrossOrigin
public class WordCardController {

    private final WordCardService wordCardService;

    public WordCardController(WordCardService wordCardService) {
        this.wordCardService = wordCardService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "successfil operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WordCardDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)
                    )
            )
    })
    @Operation(description = "Getting the Word Card ")
    @GetMapping("/{id}")
    public ResponseEntity<WordCardDto> getWordCard(@PathVariable Long id){
        return ResponseEntity.ok(wordCardService.findById(id));
    }

    @PostMapping
    public ResponseEntity<WordCardDto> createWordCard(@RequestBody WordCardDto wordCardDto){
        return ResponseEntity.ok(wordCardService.createWordCard(wordCardDto)) ;
    }

    @PutMapping
    public ResponseEntity<WordCardDto> updateWordCard(@RequestBody WordCardDto WordCardDto){
        return ResponseEntity.ok(wordCardService.updateWordCard(WordCardDto));
    }

    @DeleteMapping("/{id}")
    public void deleteWordCard(@PathVariable Long id){
        wordCardService.deleteWordCardById(id);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<WordCardDto>> searchWordCards(@RequestBody WordCardSearchRequest searchRequest){
        return ResponseEntity.ok().body(wordCardService.searchWordCards(searchRequest));
    }

}
