package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.tag.TagSearchRequest;
import app.prog.evv.drillang.dto.tag.Tag;
import app.prog.evv.drillang.entity.TagEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TagMapper;
import app.prog.evv.drillang.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public Tag getById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("tag entity not found by (id=%d)", id)));
    }

    @Override
    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
        LOGGER.info("tag deleted id=" + id);
    }

    @Override
    public Tag updateTag(Tag tag) {
        Optional<TagEntity> existing = tagRepository.findById(tag.getId());
        Tag updated = new Tag();
        if(existing.isPresent()){
            updated = tagMapper.toDto(tagRepository.save(tagMapper.toEntity(tag)));
        }
        return updated;
    }

    @Override
    public Tag createTag(Tag tag) {
        TagEntity created = tagRepository.save(tagMapper.toEntity(tag));
        return tagMapper.toDto(created);
    }

    @Override
    public List<Tag> searchTags(TagSearchRequest searchRequest) {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDto)
                .collect(Collectors.toList());
    }
}
