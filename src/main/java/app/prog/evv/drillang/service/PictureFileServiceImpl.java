package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.PictureFileDto;
import app.prog.evv.drillang.dto.PictureFileSearchRequest;
import app.prog.evv.drillang.entity.AudioFile;
import app.prog.evv.drillang.entity.PictureFile;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.PictureFileMapper;
import app.prog.evv.drillang.repository.PictureFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureFileServiceImpl implements PictureFileService {

    private final PictureFileRepository pictureFileRepository;

    private final PictureFileMapper pictureFileMapper;

    public PictureFileServiceImpl(PictureFileRepository pictureFileRepository, PictureFileMapper pictureFileMapper) {
        this.pictureFileRepository = pictureFileRepository;
        this.pictureFileMapper = pictureFileMapper;
    }

    @Override
    public PictureFileDto findById(Long id) {
        return pictureFileRepository.findById(id)
                .map(pictureFileMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("pucture file not found (id=%d)", id)));
    }

    @Override
    public PictureFileDto createPictureFile(MultipartFile pictureFile) {
        PictureFile file = new PictureFile();
        file.setFileName(pictureFile.getOriginalFilename());
        try {
            file.setContent(pictureFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.setContentType(pictureFile.getContentType());
        file.setSize(pictureFile.getSize());
        file.setCreatedDate(Instant.now());

        PictureFile created = pictureFileRepository.save(file);
        return pictureFileMapper.toDto(created);
    }

    @Override
    public PictureFileDto updatePictureFile(PictureFileDto pictureFileDto) {
        Optional<PictureFile> existing = pictureFileRepository.findById(pictureFileDto.getId());
        PictureFileDto edited = new PictureFileDto();
        if(existing.isPresent()){
            edited = pictureFileMapper.toDto(pictureFileRepository.save(pictureFileMapper.toEntity(pictureFileDto)));
        }
        return edited;
    }

    @Override
    public void deletePictureFileById(Long id) {
        pictureFileRepository.deleteById(id);
    }

    @Override
    public List<PictureFileDto> searchPictureFiles(PictureFileSearchRequest request) {
        return pictureFileRepository.findAll()
                .stream()
                .map(pictureFileMapper::toDto)
                .collect(Collectors.toList());
    }
}
