package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.TranslateWLessonInfo;
import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.dto.lesson.TranslateWordLessonSearchRequest;
import app.prog.evv.drillang.entity.TranslateWordLessonEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TranslateWordLessonMapper;
import app.prog.evv.drillang.repository.TranslateWordLessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TranslateWordLessonServiceImpl implements TranslateWordLessonService {

    private final TranslateWordLessonMapper translateWordLessonMapper;
    private final TranslateWordLessonRepository translateWordLessonRepository;

    public TranslateWordLessonServiceImpl(TranslateWordLessonMapper translateWordLessonMapper,
                                          TranslateWordLessonRepository translateWordLessonRepository) {
        this.translateWordLessonMapper = translateWordLessonMapper;
        this.translateWordLessonRepository = translateWordLessonRepository;
    }

    @Override
    public TranslateWordLesson findById(Long id) {
        TranslateWordLessonEntity entity = translateWordLessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("translate word lesson entity not found (id=%d)", id)));
        return translateWordLessonMapper.toDto(entity);
    }

    @Override
    public TranslateWordLesson createTranslateWordLesson(TranslateWordLesson translateWordLesson) {
        TranslateWordLessonEntity created = translateWordLessonRepository.save(translateWordLessonMapper.toEntity(translateWordLesson));
        return translateWordLessonMapper.toDto(created);
    }

    @Override
    public TranslateWordLesson updateTranslateWordLesson(TranslateWordLesson translateWordLesson) {
        Optional<TranslateWordLessonEntity> existing = translateWordLessonRepository.findById(translateWordLesson.getId());
        TranslateWordLesson updated = new TranslateWordLesson();
        if(existing.isPresent()){
            updated = translateWordLessonMapper.toDto(
                    translateWordLessonRepository.save(translateWordLessonMapper.toEntity(translateWordLesson))
            );
        }
        return updated;
    }

    @Override
    public TranslateWordLesson learnAgainTranslateWordLesson(TranslateWordLesson translateWordLesson) {
        Optional<TranslateWordLessonEntity> existing = translateWordLessonRepository.findById(translateWordLesson.getId());
        TranslateWordLesson updated = new TranslateWordLesson();
        if(existing.isPresent()){
            existing.get().setCorrectAnswer(0);
            existing.get().setSkip(false);
            updated = translateWordLessonMapper.toDto(translateWordLessonRepository.save(existing.get()));
        }
        return updated;
    }

    @Override
    public TranslateWordLesson skipTranslateWordLesson(TranslateWordLesson translateWordLesson) {
        Optional<TranslateWordLessonEntity> existing = translateWordLessonRepository.findById(translateWordLesson.getId());
        TranslateWordLesson updated = new TranslateWordLesson();
        if(existing.isPresent()){
            existing.get().setSkip(true);
            updated = translateWordLessonMapper.toDto(translateWordLessonRepository.save(existing.get()));
        }
        return updated;
    }

    @Override
    public List<TranslateWLessonInfo> updateTranslateWordLessons(List<TranslateWordLesson> translateWordLessons) {
        Map<Long, TranslateWordLesson> map = translateWordLessons.stream()
                .collect(Collectors.toMap(item -> item.getId(), Function.identity()));

        List<TranslateWordLessonEntity> entities = translateWordLessonRepository.findAllById(map.keySet()).stream()
                .map(entity -> {
                    TranslateWordLesson trLesson = map.get(entity.getId());
                    entity.setAllAnswer(trLesson.getAllAnswer());
                    entity.setCorrectAnswer(trLesson.getCorrectAnswer());
                    entity.setTargetAnswer(trLesson.getTargetAnswer());
                    entity.setCountDone(trLesson.getCountDone());
                    return entity;
                })
                .collect(Collectors.toList());

        List<TranslateWLessonInfo> result = translateWordLessonRepository.saveAll(entities).stream()
                .map(translateWordLessonMapper::toInfoDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void deleteTranslateWordLessonById(Long id) {
        translateWordLessonRepository.deleteById(id);
    }

    @Override
    public Page<TranslateWordLesson> searchTranslateWordLessons(TranslateWordLessonSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return translateWordLessonRepository.findAll(pageRequest).map(translateWordLessonMapper::toDto);
    }

    @Override
    public List<TranslateWLessonInfo> getTranslatesForLesson(Long lessonId) {
        List<TranslateWordLessonEntity> result = translateWordLessonRepository.findByWordLessonIdOrderByIdDesc(lessonId);
        return result.stream().map(translateWordLessonMapper::toInfoDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setLearnLessonAgain(@NotNull Long lessonId) {
        translateWordLessonRepository.setLessonLearnAgain(lessonId);
    }
}
