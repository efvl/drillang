package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.testCard.TCardSourceInfo;
import app.prog.evv.drillang.dto.testCard.TestCardDto;
import app.prog.evv.drillang.dto.testCard.TestCardSearchRequest;
import app.prog.evv.drillang.dto.testCard.TestCardSourceDto;
import app.prog.evv.drillang.dto.testLesson.TestCardTestLessonSearchRequest;
import app.prog.evv.drillang.entity.SourceInfoEntity;
import app.prog.evv.drillang.entity.TestCardEntity;
import app.prog.evv.drillang.entity.TestCardSourceEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TestCardMapper;
import app.prog.evv.drillang.mapper.TestCardSourceMapper;
import app.prog.evv.drillang.repository.SourceInfoRepository;
import app.prog.evv.drillang.repository.TestCardRepository;
import app.prog.evv.drillang.repository.TestCardSourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestCardServiceImpl implements TestCardService {

    private final TestCardRepository testCardRepository;
    private final SourceInfoRepository sourceInfoRepository;
    private final TestCardSourceRepository testCardSourceRepository;
    private final TestCardMapper testCardMapper;

    public TestCardServiceImpl(TestCardRepository testCardRepository, SourceInfoRepository sourceInfoRepository, TestCardSourceRepository testCardSourceRepository, TestCardMapper testCardMapper) {
        this.testCardRepository = testCardRepository;
        this.sourceInfoRepository = sourceInfoRepository;
        this.testCardSourceRepository = testCardSourceRepository;
        this.testCardMapper = testCardMapper;
    }

    @Override
    public TestCardDto findById(Long id) {
        TestCardEntity entity = testCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("test card not found (id=%d)", id)));
        return testCardMapper.toDto(entity);
    }

    @Override
    public TestCardDto createTestCard(TestCardDto testCardDto) {
        testCardDto.setDateCreated(Instant.now());
        TestCardEntity created = testCardRepository.save(testCardMapper.toEntity(testCardDto));
        Set<TestCardSourceEntity> sources = created.getSources().stream()
                .peek(src -> src.setTestCard(created))
                .collect(Collectors.toSet());
        created.setSources(sources);
        testCardRepository.save(created);
        return testCardMapper.toDto(created);
    }

    @Override
    public TestCardDto updateTestCard(TestCardDto testCardDto) {
        Optional<TestCardEntity> existing = testCardRepository.findById(testCardDto.getId());
        TestCardEntity toUpdateEntity = testCardMapper.toEntity(testCardDto);
        List<Long> updIds = toUpdateEntity.getSources().stream()
                .filter(s -> s.getId() != null)
                .map(s -> s.getId())
                .collect(Collectors.toList());
        TestCardDto updated = new TestCardDto();
        if(existing.isPresent()){
            List<Long> idsToDelete = existing.get().getSources().stream()
                    .filter(s -> updIds.size() == 0 || !updIds.contains(s.getId()))
                    .map(s -> s.getId())
                    .collect(Collectors.toList());
            if (idsToDelete.size() > 0) {
                testCardSourceRepository.deleteAllById(idsToDelete);
            }

            Set<TestCardSourceEntity> sources = toUpdateEntity.getSources().stream()
                    .peek(src -> src.setTestCard(toUpdateEntity))
                    .collect(Collectors.toSet());
            toUpdateEntity.setSources(sources);
            updated = testCardMapper.toDto(testCardRepository.save(toUpdateEntity));
        }
        return updated;
    }

    @Override
    public void deleteTestCardById(Long id) {
        testCardRepository.deleteById(id);
    }

    @Override
    public Page<TestCardDto> searchTestCards(TestCardSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return testCardRepository.search(request, pageRequest).map(testCardMapper::toDto);
    }

    @Override
    public Page<TestCardDto> searchCardsNotInLesson(TestCardTestLessonSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return testCardRepository.searchCardsNotInLesson(request, pageRequest).map(testCardMapper::toDto);
    }
}
