package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.LanguageDto;
import app.prog.evv.drillang.dto.LanguageSearchRequest;

import java.util.List;

public interface LangService {

    LanguageDto findById(Long id);

    void deleteLanguageById(Long id);

    LanguageDto updateLanguage(LanguageDto languageDto);

    LanguageDto createLanguage(LanguageDto languageDto);

    List<LanguageDto> search(LanguageSearchRequest searchRequest);
}
