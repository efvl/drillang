package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.WordCardDto;
import app.prog.evv.drillang.dto.WordCardSearchRequest;

import java.util.List;

public interface WordCardService {

    WordCardDto findById(Long id);

    WordCardDto createWordCard(WordCardDto wordCardDto);

    WordCardDto updateWordCard(WordCardDto wordCardDto);

    void deleteWordCardById(Long id);

    List<WordCardDto> searchWordCards(WordCardSearchRequest request);

}
