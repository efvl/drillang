package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import app.prog.evv.drillang.dto.lang.LanguageSearchRequest;
import app.prog.evv.drillang.entity.LanguageEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.LangMapper;
import app.prog.evv.drillang.repository.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LangServiceImpl implements LangService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LangServiceImpl.class);
    private final LangRepository langRepository;
    private final LangMapper langMapper;


    public LangServiceImpl(LangRepository langRepository, LangMapper langMapper) {
        this.langRepository = langRepository;
        this.langMapper = langMapper;
    }


    @Override
    public LanguageDto findById(Long id) {
        return langRepository.findById(id)
                .map(langMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("language not found (id=%d)", id)));
    }

    @Override
    public void deleteLanguageById(Long id) {
        langRepository.deleteById(id);
    }

    @Override
    public LanguageDto updateLanguage(LanguageDto languageDto) {
        Optional<LanguageEntity> existing = langRepository.findById(languageDto.getId());
        LanguageDto updated = new LanguageDto();
        if(existing.isPresent()){
            updated = langMapper.toDto(langRepository.save(langMapper.toEntity(languageDto)));
        }
        return updated;
    }

    @Override
    public LanguageDto createLanguage(LanguageDto languageDto) {
        LanguageEntity created = langRepository.save(langMapper.toEntity(languageDto));
        return langMapper.toDto(created);
    }

    @Override
    public List<LanguageDto> search(LanguageSearchRequest searchRequest) {
        return langRepository.findAll().stream()
                .map(langMapper::toDto)
                .collect(Collectors.toList());
    }

}
