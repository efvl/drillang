package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.testLesson.TCardTLessonInfo;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonDto;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import app.prog.evv.drillang.entity.TestCardTestLessonEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TestCardTestLessonMapper;
import app.prog.evv.drillang.repository.TestCardTestLessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TestCardTestLessonServiceImpl implements TestCardTestLessonService {

    private final TestCardTestLessonRepository tCTLessonRepository;
    private final TestCardTestLessonMapper tCTLessonMapper;

    public TestCardTestLessonServiceImpl(TestCardTestLessonRepository tCTLessonRepository, TestCardTestLessonMapper tCTLessonMapper) {
        this.tCTLessonRepository = tCTLessonRepository;
        this.tCTLessonMapper = tCTLessonMapper;
    }

    @Override
    public TestCardTestLessonDto findById(Long id) {
        TestCardTestLessonEntity entity = tCTLessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("test card lesson entity not found (id=%d)", id)));
        return tCTLessonMapper.toDto(entity);
    }

    @Override
    public TestCardTestLessonDto createTCardTLesson(TestCardTestLessonDto tCardTLesson) {
        TestCardTestLessonEntity created = tCTLessonRepository.save(tCTLessonMapper.toEntity(tCardTLesson));
        return tCTLessonMapper.toDto(created);
    }

    @Override
    public TestCardTestLessonDto updateTCardTLesson(TestCardTestLessonDto tCardTLesson) {
        Optional<TestCardTestLessonEntity> existing = tCTLessonRepository.findById(tCardTLesson.getId());
        TestCardTestLessonDto updated = new TestCardTestLessonDto();
        if(existing.isPresent()){
            updated = tCTLessonMapper.toDto(
                    tCTLessonRepository.save(tCTLessonMapper.toEntity(tCardTLesson))
            );
        }
        return updated;
    }

    @Override
    public void deleteTCardTLessonById(Long id) {
        tCTLessonRepository.deleteById(id);
    }

    @Override
    public Page<TestCardTestLessonDto> searchTCardTLessons(TestCardTestLessonSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return tCTLessonRepository.findAll(pageRequest).map(tCTLessonMapper::toDto);
    }

    @Override
    public List<TCardTLessonInfo> getTCardsForLesson(Long lessonId) {
        List<TestCardTestLessonEntity> result = tCTLessonRepository.findByTestLessonIdOrderByIdDesc(lessonId);
        return result.stream().map(tCTLessonMapper::toInfoDto).collect(Collectors.toList());
    }

    @Override
    public TestCardTestLessonDto learnAgainTCardTLesson(TestCardTestLessonDto tCardTLesson) {
        Optional<TestCardTestLessonEntity> existing = tCTLessonRepository.findById(tCardTLesson.getId());
        TestCardTestLessonDto updated = new TestCardTestLessonDto();
        if(existing.isPresent()){
            existing.get().setCorrectAnswer(0);
            existing.get().setSkip(false);
            updated = tCTLessonMapper.toDto(tCTLessonRepository.save(existing.get()));
        }
        return updated;
    }

    @Override
    public TCardTLessonInfo skipTCardTLesson(TCardTLessonInfo tCardTLessonInfo) {
        Optional<TestCardTestLessonEntity> existing = tCTLessonRepository.findById(tCardTLessonInfo.getId());
        TCardTLessonInfo updated = new TCardTLessonInfo();
        if(existing.isPresent()){
            existing.get().setSkip(!existing.get().isSkip());
            updated = tCTLessonMapper.toInfoDto(tCTLessonRepository.save(existing.get()));
        }
        return updated;
    }

    @Override
    public List<TCardTLessonInfo> updateTCardTLessons(List<TestCardTestLessonDto> tCardTLessons) {
        Map<Long, TestCardTestLessonDto> map = tCardTLessons.stream()
                .collect(Collectors.toMap(item -> item.getId(), Function.identity()));

        List<TestCardTestLessonEntity> entities = tCTLessonRepository.findAllById(map.keySet()).stream()
                .map(entity -> {
                    TestCardTestLessonDto tctLesson = map.get(entity.getId());
                    entity.setAllAnswer(tctLesson.getAllAnswer());
                    entity.setCorrectAnswer(tctLesson.getCorrectAnswer());
                    entity.setTargetAnswer(tctLesson.getTargetAnswer());
                    entity.setCountDone(tctLesson.getCountDone());
                    return entity;
                })
                .collect(Collectors.toList());

        List<TCardTLessonInfo> result = tCTLessonRepository.saveAll(entities).stream()
                .map(tCTLessonMapper::toInfoDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public void setLearnLessonAgain(Long lessonId) {
        tCTLessonRepository.setLessonLearnAgain(lessonId);
    }
}
