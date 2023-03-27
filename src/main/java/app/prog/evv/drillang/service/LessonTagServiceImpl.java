package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lessonTag.LessonTagDto;
import app.prog.evv.drillang.dto.lessonTag.LessonTagSearchRequest;
import app.prog.evv.drillang.entity.LessonTagEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.LessonTagMapper;
import app.prog.evv.drillang.repository.LessonTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonTagServiceImpl implements LessonTagService {

    private final LessonTagRepository tagRepository;
    private final LessonTagMapper tagMapper;

    public LessonTagServiceImpl(LessonTagRepository tagRepository, LessonTagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public LessonTagDto getById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("lesson tag entity not found by (id=%d)", id)));
    }

    @Override
    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public LessonTagDto updateTag(LessonTagDto lessonTag) {
        Optional<LessonTagEntity> existing = tagRepository.findById(lessonTag.getId());
        LessonTagDto updated = new LessonTagDto();
        if(existing.isPresent()){
            updated = tagMapper.toDto(tagRepository.save(tagMapper.toEntity(lessonTag)));
        }
        return updated;
    }

    @Override
    public LessonTagDto createTag(LessonTagDto lessonTag) {
        LessonTagEntity created = tagRepository.save(tagMapper.toEntity(lessonTag));
        return tagMapper.toDto(created);
    }

    @Override
    public List<LessonTagDto> searchTags(LessonTagSearchRequest searchRequest) {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDto)
                .collect(Collectors.toList());
    }
}
