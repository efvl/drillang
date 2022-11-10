package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.AudioFileDto;
import app.prog.evv.drillang.dto.PictureFileDto;
import app.prog.evv.drillang.dto.PictureFileSearchRequest;
import app.prog.evv.drillang.exception.ApiError;
import app.prog.evv.drillang.service.PictureFileService;
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
@RequestMapping(path = "/v1/picture/")
public class PictureFileController {

    private final PictureFileService pictureFileService;

    public PictureFileController(PictureFileService pictureFileService) {
        this.pictureFileService = pictureFileService;
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
    @Operation(description = "Getting the Picture File ")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPictureFile(@PathVariable Long id){
        PictureFileDto pictureFileDto = pictureFileService.findById(id);
        ByteArrayResource resource = new ByteArrayResource(pictureFileDto.getContent());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pictureFileDto.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pictureFileDto.getFileName() + "\"")
                .body(resource);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PictureFileDto> createPictureFile(@RequestPart MultipartFile pictureFile){
        return ResponseEntity.ok(pictureFileService.createPictureFile(pictureFile)) ;
    }

    @PutMapping
    public ResponseEntity<PictureFileDto> updatePictureFile(@RequestBody PictureFileDto PictureFileDto){
        return ResponseEntity.ok(pictureFileService.updatePictureFile(PictureFileDto));
    }

    @DeleteMapping("/{id}")
    public void deletePictureFile(@PathVariable Long id){
        pictureFileService.deletePictureFileById(id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PictureFileDto>> searchPictureFiles(@RequestBody PictureFileSearchRequest searchRequest){
        return ResponseEntity.ok().body(pictureFileService.searchPictureFiles(searchRequest));
    }
}
