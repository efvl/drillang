package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.wordAudio.AudioFileDto;
import app.prog.evv.drillang.dto.wordAudio.AudioFileInfo;
import app.prog.evv.drillang.dto.wordAudio.AudioFileSearchRequest;
import app.prog.evv.drillang.exception.ApiError;
import app.prog.evv.drillang.service.AudioFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "v1/audio")
@CrossOrigin
public class AudioFileController {

    private final AudioFileService audioFileService;


    public AudioFileController(AudioFileService audioFileService) {
        this.audioFileService = audioFileService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "successfil operation",
                    content = @Content(
                        schema = @Schema(implementation = Resource.class)
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
    @Operation(description = "Getting the Audio File ")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getAudioFile(@PathVariable Long id){
        AudioFileDto audioFileDto = audioFileService.findById(id);
        ByteArrayResource resource = new ByteArrayResource(audioFileDto.getContent());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(audioFileDto.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + audioFileDto.getFileName() + "\"")
                .body(resource);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AudioFileInfo> createAudioFile(@RequestParam(name = "audFile") MultipartFile audioFile){
        return ResponseEntity.ok(audioFileService.createAudioFile(audioFile));
    }

    @PutMapping
    public ResponseEntity<AudioFileInfo> updateAudioFile(@RequestBody AudioFileDto AudioFileDto){
        return ResponseEntity.ok(audioFileService.updateAudioFile(AudioFileDto));
    }

    @DeleteMapping("/{id}")
    public void deleteAudioFile(@PathVariable Long id){
        audioFileService.deleteAudioFileById(id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<AudioFileDto>> searchAudioFiles(@RequestBody AudioFileSearchRequest searchRequest){
        return ResponseEntity.ok().body(audioFileService.searchAudioFiles(searchRequest));
    }
}
