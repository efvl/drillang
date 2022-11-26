package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.dto.lesson.TranslateWordLessonSearchRequest;
import app.prog.evv.drillang.entity.TranslateWordLessonEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TranslateWordLessonMapper;
import app.prog.evv.drillang.repository.TranslateWordLessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public void deleteTranslateWordLessonById(Long id) {
        translateWordLessonRepository.deleteById(id);
    }

    @Override
    public Page<TranslateWordLesson> searchTranslateWordLessons(TranslateWordLessonSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return translateWordLessonRepository.findAll(pageRequest).map(translateWordLessonMapper::toDto);
    }
}
