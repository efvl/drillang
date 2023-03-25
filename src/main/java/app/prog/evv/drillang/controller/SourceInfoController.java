package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.source.SourceInfoDto;
import app.prog.evv.drillang.dto.source.SourceInfoSearchRequest;
import app.prog.evv.drillang.service.SourceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/v1/source-info")
@CrossOrigin
public class SourceInfoController {

    private final SourceInfoService sourceInfoService;

    public SourceInfoController(SourceInfoService sourceInfoService) {
        this.sourceInfoService = sourceInfoService;
    }

    @Operation(description = "Getting the Source Info ")
    @GetMapping("/{id}")
    public ResponseEntity<SourceInfoDto> getSourceInfo(@PathVariable Long id){
        return ResponseEntity.ok(sourceInfoService.findById(id));
    }

    @Operation(description = "Create new Source Info")
    @PostMapping
    public ResponseEntity<SourceInfoDto> createSourceInfo(@RequestBody SourceInfoDto sourceInfoDto){
        return ResponseEntity.ok(sourceInfoService.createSourceInfo(sourceInfoDto)) ;
    }

    @Operation(description = "Update Source Info")
    @PutMapping
    public ResponseEntity<SourceInfoDto> updateSourceInfo(@RequestBody SourceInfoDto sourceInfoDto){
        return ResponseEntity.ok(sourceInfoService.updateSourceInfo(sourceInfoDto));
    }

    @Operation(description = "Delete Source Info")
    @DeleteMapping("/{id}")
    public void deleteSourceInfo(@PathVariable Long id){
        sourceInfoService.deleteSourceInfoById(id);
    }

    @Operation(description = "Search Sources Info by searching params")
    @PostMapping("/search")
    public ResponseEntity<Page<SourceInfoDto>> searchSourcesInfo(@RequestBody SourceInfoSearchRequest searchRequest){
        return ResponseEntity.ok().body(sourceInfoService.searchSourceInfo(searchRequest));
    }

}
