package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordCard.WordCardDto;
import app.prog.evv.drillang.dto.wordCard.WordCardSearchRequest;
import app.prog.evv.drillang.entity.WordCardEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.WordCardMapper;
import app.prog.evv.drillang.repository.WordCardRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class WordCardServiceImpl implements WordCardService {

    private final WordCardRepository wordCardRepository;
    private final WordCardMapper wordCardMapper;
    private final PictureFileService pictureFileService;


    public WordCardServiceImpl(WordCardRepository wordCardRepository, WordCardMapper wordCardMapper, PictureFileService pictureFileService) {
        this.wordCardRepository = wordCardRepository;
        this.wordCardMapper = wordCardMapper;
        this.pictureFileService = pictureFileService;
    }

    @Override
    public WordCardDto findById(Long id) {
        WordCardEntity entity = wordCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("word card not found (id=%d)", id)));
        return wordCardMapper.toDto(entity);
    }

    @Override
    public WordCardDto createWordCard(WordCardDto wordCardDto) {
        wordCardDto.setDateCreated(Instant.now());
        WordCardEntity created = wordCardRepository.save(wordCardMapper.toEntity(wordCardDto));
        return wordCardMapper.toDto(created);
    }

    @Override
    public WordCardDto updateWordCard(WordCardDto wordCardDto) {
        Optional<WordCardEntity> existing = wordCardRepository.findById(wordCardDto.getId());
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
    public Page<WordCardDto> searchWordCards(WordCardSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return wordCardRepository.search(request, pageRequest).map(wordCardMapper::toDto);
    }
}
