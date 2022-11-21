package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordPicture.PictureFileDto;
import app.prog.evv.drillang.dto.wordPicture.PictureFileSearchRequest;
import app.prog.evv.drillang.dto.wordPicture.PictureFileInfo;
import app.prog.evv.drillang.entity.PictureFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureFileService {

    PictureFileDto findById(Long id);

    PictureFile findEntityById(Long id);

    boolean isExistsById(Long id);

    PictureFileInfo createPictureFile(MultipartFile pictureFileDto);

    PictureFileDto updatePictureFile(PictureFileDto pictureFileDto);

    void deletePictureFileById(Long id);

    List<PictureFileDto> searchPictureFiles(PictureFileSearchRequest request);

}
