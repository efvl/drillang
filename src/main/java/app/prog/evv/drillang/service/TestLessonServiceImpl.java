package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.testLesson.TestLessonDto;
import app.prog.evv.drillang.dto.testLesson.TestLessonSearchRequest;
import app.prog.evv.drillang.entity.TestLessonEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TestLessonMapper;
import app.prog.evv.drillang.repository.TestLessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestLessonServiceImpl implements TestLessonService {

    private final TestLessonMapper testLessonMapper;
    private final TestLessonRepository testLessonRepository;

    public TestLessonServiceImpl(TestLessonMapper testLessonMapper, TestLessonRepository testLessonRepository) {
        this.testLessonMapper = testLessonMapper;
        this.testLessonRepository = testLessonRepository;
    }

    @Override
    public TestLessonDto findById(Long id) {
        TestLessonEntity entity = testLessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("word lesson entity not found (id=%d)", id)));
        return testLessonMapper.toDto(entity);
    }

    @Override
    public TestLessonDto createTestLesson(TestLessonDto testLesson) {
        TestLessonEntity created = testLessonRepository.save(testLessonMapper.toEntity(testLesson));
        return testLessonMapper.toDto(created);
    }

    @Override
    public TestLessonDto updateTestLesson(TestLessonDto testLesson) {
        Optional<TestLessonEntity> existing = testLessonRepository.findById(testLesson.getId());
        TestLessonDto updated = new TestLessonDto();
        if(existing.isPresent()){
            updated = testLessonMapper.toDto(testLessonRepository.save(testLessonMapper.toEntity(testLesson)));
        }
        return updated;
    }

    @Override
    public void deleteTestLessonById(Long id) {
        testLessonRepository.deleteById(id);
    }

    @Override
    public Page<TestLessonDto> searchTestLessons(TestLessonSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return testLessonRepository.findAll(pageRequest).map(testLessonMapper::toDto);
    }

}
