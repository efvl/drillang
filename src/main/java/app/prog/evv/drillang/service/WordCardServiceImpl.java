package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.WordCardDto;
import app.prog.evv.drillang.dto.WordCardSearchRequest;
import app.prog.evv.drillang.entity.WordCard;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.WordCardMapper;
import app.prog.evv.drillang.repository.WordCardRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordCardServiceImpl implements WordCardService {

    private final WordCardRepository wordCardRepository;

    private final WordCardMapper wordCardMapper;

    public WordCardServiceImpl(WordCardRepository wordCardRepository, WordCardMapper wordCardMapper) {
        this.wordCardRepository = wordCardRepository;
        this.wordCardMapper = wordCardMapper;
    }

    @Override
    public WordCardDto findById(Long id) {
        return wordCardRepository.findById(id)
                .map(wordCardMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("word card not found (id=%d)", id)));
    }

    @Override
    public WordCardDto createWordCard(WordCardDto wordCardDto) {
        wordCardDto.setDateCreated(Instant.now());
        WordCard created = wordCardRepository.save(wordCardMapper.toEntity(wordCardDto));
        return wordCardMapper.toDto(created);
    }

    @Override
    public WordCardDto updateWordCard(WordCardDto wordCardDto) {
        Optional<WordCard> existing = wordCardRepository.findById(wordCardDto.getId());
        WordCardDto updated = new WordCardDto();
        if(existing.isPresent()){
            updated = wordCardMapper.toDto(wordCardRepository.save(wordCardMapper.toEntity(wordCardDto)));
        }
        return updated;
    }

    @Override
    public void deleteWordCardById(Long id) {
        wordCardRepository.deleteById(id);
    }

    @Override
    public List<WordCardDto> searchWordCards(WordCardSearchRequest request) {
        return wordCardRepository.findAll()
                .stream()
                .map(wordCardMapper::toDto)
                .collect(Collectors.toList());
    }
}
