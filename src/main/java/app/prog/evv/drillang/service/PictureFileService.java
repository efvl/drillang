package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.PictureFileDto;
import app.prog.evv.drillang.dto.PictureFileSearchRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureFileService {

    PictureFileDto findById(Long id);

    PictureFileDto createPictureFile(MultipartFile pictureFileDto);

    PictureFileDto updatePictureFile(PictureFileDto pictureFileDto);

    void deletePictureFileById(Long id);

    List<PictureFileDto> searchPictureFiles(PictureFileSearchRequest request);

}
