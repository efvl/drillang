package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.AudioFileDto;
import app.prog.evv.drillang.dto.AudioFileSearchRequest;
import app.prog.evv.drillang.entity.AudioFile;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.AudioFileMapper;
import app.prog.evv.drillang.repository.AudioFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
                .orElseThrow(() ->new EntityNotFoundException(String.format("audio file not found (id=%d)", id)));
    }

    @Override
    public AudioFileDto createAudioFile(MultipartFile audioFile) {
        AudioFile file = new AudioFile();
        file.setFileName(audioFile.getOriginalFilename());
        try {
            file.setContent(audioFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.setContentType(audioFile.getContentType());
        file.setSize(audioFile.getSize());
        file.setCreatedDate(Instant.now());

        AudioFile created = audioFileRepository.save(file);
        return audioFileMapper.toDto(created);
    }

    @Override
    public AudioFileDto updateAudioFile(AudioFileDto audioFileDto) {
        Optional<AudioFile> existing = audioFileRepository.findById(audioFileDto.getId());
        AudioFileDto updated = new AudioFileDto();
        if(existing.isPresent()){
            updated = audioFileMapper.toDto(audioFileRepository.save(audioFileMapper.toEntity(audioFileDto)));
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
