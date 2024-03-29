package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.WordLesson;
import app.prog.evv.drillang.dto.lesson.WordLessonSearchRequest;
import app.prog.evv.drillang.entity.WordLessonEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.WordLessonMapper;
import app.prog.evv.drillang.repository.TranslateWordLessonRepository;
import app.prog.evv.drillang.repository.WordLessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordLessonServiceImpl implements WordLessonService {

    private final WordLessonMapper wordLessonMapper;
    private final WordLessonRepository wordLessonRepository;

    private final TranslateWordLessonService translateWordLessonService;

    public WordLessonServiceImpl(WordLessonMapper wordLessonMapper,
                                 WordLessonRepository wordLessonRepository,
                                 TranslateWordLessonService translateWordLessonService) {
        this.wordLessonMapper = wordLessonMapper;
        this.wordLessonRepository = wordLessonRepository;
        this.translateWordLessonService = translateWordLessonService;
    }

    @Override
    public WordLesson findById(Long id) {
        WordLessonEntity entity = wordLessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("word lesson entity not found (id=%d)", id)));
        return wordLessonMapper.toDto(entity);
    }

    @Override
    public WordLesson createWordLesson(WordLesson wordLesson) {
        WordLessonEntity created = wordLessonRepository.save(wordLessonMapper.toEntity(wordLesson));
        return wordLessonMapper.toDto(created);
    }

    @Override
    public WordLesson updateWordLesson(WordLesson wordLesson) {
        Optional<WordLessonEntity> existing = wordLessonRepository.findById(wordLesson.getId());
        WordLesson updated = new WordLesson();
        if(existing.isPresent()){
            updated = wordLessonMapper.toDto(wordLessonRepository.save(wordLessonMapper.toEntity(wordLesson)));
        }
        return updated;
    }

    @Override
    public void deleteWordLessonById(Long id) {
        wordLessonRepository.deleteById(id);
    }

    @Override
    public Page<WordLesson> searchWordLessons(WordLessonSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        Page<WordLesson> lessonsPage = wordLessonRepository.findAll(pageRequest).map(wordLessonMapper::toDto);
        List<Long> ids = lessonsPage.getContent().stream()
                .map(WordLesson::getId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(ids)) {
            final Map<Long, Long> trCountsMap = translateWordLessonService.getTranslatesCountByLessons(ids);
            lessonsPage.getContent().stream()
                .forEach(wordLesson -> wordLesson.setTranslatesCount(trCountsMap.getOrDefault(wordLesson.getId(), 0L)));
        }
        return lessonsPage;
    }

    @Override
    public List<WordLesson> getLessonsByFromLanguage(@NotNull Long fromLangId) {
        return wordLessonRepository.findByFromLanguageId(fromLangId).stream()
                .map(wordLessonMapper::toDto)
                .collect(Collectors.toList());
    }
}
