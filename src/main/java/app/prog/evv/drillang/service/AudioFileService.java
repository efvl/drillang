package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordAudio.AudioFileDto;
import app.prog.evv.drillang.dto.wordAudio.AudioFileInfo;
import app.prog.evv.drillang.dto.wordAudio.AudioFileSearchRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AudioFileService {

    AudioFileDto findById(Long id);

    AudioFileInfo createAudioFile(MultipartFile audioFile);

    AudioFileInfo updateAudioFile(AudioFileDto audioFileDto);

    void deleteAudioFileById(Long id);

    List<AudioFileDto> searchAudioFiles(AudioFileSearchRequest request);

}
