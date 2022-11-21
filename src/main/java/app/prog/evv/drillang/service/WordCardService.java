package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordCard.WordCardDto;
import app.prog.evv.drillang.dto.wordCard.WordCardSearchRequest;
import org.springframework.data.domain.Page;

public interface WordCardService {

    WordCardDto findById(Long id);

    WordCardDto createWordCard(WordCardDto wordCard);

    WordCardDto updateWordCard(WordCardDto wordCardDto);

    void deleteWordCardById(Long id);

    Page<WordCardDto> searchWordCards(WordCardSearchRequest request);

}
