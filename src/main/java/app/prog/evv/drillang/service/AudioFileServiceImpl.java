package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordAudio.AudioFileDto;
import app.prog.evv.drillang.dto.wordAudio.AudioFileInfo;
import app.prog.evv.drillang.dto.wordAudio.AudioFileSearchRequest;
import app.prog.evv.drillang.entity.AudioFileEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.AudioFileMapper;
import app.prog.evv.drillang.repository.AudioFileRepository;
import app.prog.evv.drillang.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AudioFileServiceImpl implements AudioFileService {

    private final AudioFileRepository audioFileRepository;

    private final AudioFileMapper audioFileMapper;

    public AudioFileServiceImpl(AudioFileRepository audioFileRepository, AudioFileMapper audioFileMapper) {
        this.audioFileRepository = audioFileRepository;
        this.audioFileMapper = audioFileMapper;
    }


    @Override
    public AudioFileDto findById(Long id) {
        return audioFileRepository.findById(id)
                .map(audioFileMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("audio file not found (id=%d)", id)));
    }

    @Override
    public AudioFileInfo createAudioFile(MultipartFile audioFile) {
        AudioFileEntity file = new AudioFileEntity();
        file.setFileName(audioFile.getOriginalFilename());
        try {
            String checksum = FileUtils.calcChecksum(audioFile.getBytes());
            Optional<AudioFileEntity> existing = audioFileRepository.findByChecksum(checksum)
                    .stream().findFirst();
            if(existing.isPresent()){
                return audioFileMapper.toAudioInfo(existing.get());
            }
            file.setContent(audioFile.getBytes());
            file.setChecksum(checksum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        file.setContentType(audioFile.getContentType());
        file.setContentType(audioFile.getContentType());
        file.setSize(audioFile.getSize());
        file.setCreatedDate(Instant.now());
        AudioFileEntity created = audioFileRepository.save(file);
        return audioFileMapper.toAudioInfo(created);
    }

    @Override
    public AudioFileInfo updateAudioFile(AudioFileDto audioFileDto) {
        Optional<AudioFileEntity> existing = audioFileRepository.findById(audioFileDto.getId());
        AudioFileInfo updated = new AudioFileInfo();
        if(existing.isPresent()){
            updated = audioFileMapper.toAudioInfo(audioFileRepository.save(audioFileMapper.toEntity(audioFileDto)));
        }
        return updated;
    }

    @Override
    public void deleteAudioFileById(Long id) {
        audioFileRepository.deleteById(id);
    }

    @Override
    public List<AudioFileDto> searchAudioFiles(AudioFileSearchRequest request) {
        return audioFileRepository.findAll()
                .stream()
                .map(audioFileMapper::toDto)
                .collect(Collectors.toList());
    }

}
