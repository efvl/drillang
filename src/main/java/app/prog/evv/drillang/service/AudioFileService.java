package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.AudioFileDto;
import app.prog.evv.drillang.dto.AudioFileSearchRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AudioFileService {

    AudioFileDto findById(Long id);

    AudioFileDto createAudioFile(MultipartFile audioFile);

    AudioFileDto updateAudioFile(AudioFileDto audioFileDto);

    void deleteAudioFileById(Long id);

    List<AudioFileDto> searchAudioFiles(AudioFileSearchRequest request);

}
