package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.testCard.TestCardDto;
import app.prog.evv.drillang.dto.testCard.TestCardSearchRequest;
import app.prog.evv.drillang.entity.TestCardEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TestCardMapper;
import app.prog.evv.drillang.repository.TestCardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TestCardServiceImpl implements TestCardService {

    private final TestCardRepository testCardRepository;
    private final TestCardMapper testCardMapper;

    public TestCardServiceImpl(TestCardRepository testCardRepository, TestCardMapper testCardMapper) {
        this.testCardRepository = testCardRepository;
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
        return testCardMapper.toDto(created);
    }

    @Override
    public TestCardDto updateTestCard(TestCardDto testCardDto) {
        Optional<TestCardEntity> existing = testCardRepository.findById(testCardDto.getId());
        TestCardDto updated = new TestCardDto();
        if(existing.isPresent()){
            updated = testCardMapper.toDto(testCardRepository.save(testCardMapper.toEntity(testCardDto)));
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
}
