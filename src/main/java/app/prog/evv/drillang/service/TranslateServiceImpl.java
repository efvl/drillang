package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.translate.Translate;
import app.prog.evv.drillang.dto.translate.TranslateSearchRequest;
import app.prog.evv.drillang.entity.TranslateEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TranslateMapper;
import app.prog.evv.drillang.repository.TranslateRepository;
import app.prog.evv.drillang.repository.WordCardRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class TranslateServiceImpl implements TranslateService {

    private final TranslateRepository translateRepository;
    private final WordCardRepository wordCardRepository;
    private final TranslateMapper translateMapper;

    public TranslateServiceImpl(TranslateRepository translateRepository, WordCardRepository wordCardRepository, TranslateMapper translateMapper) {
        this.translateRepository = translateRepository;
        this.wordCardRepository = wordCardRepository;
        this.translateMapper = translateMapper;
    }

    @Override
    public Translate findById(Long id) {
        TranslateEntity created = translateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("translate not found (id=%d)", id)));
        return translateMapper.toDto(created);
    }

    @Override
    public Translate createTranslate(@NotNull Translate translate) {
        TranslateEntity created = new TranslateEntity();
        if(translate.getWord1() == null){
            throw new IllegalArgumentException("worCard1 for translate is null");
        }
        if(wordCardRepository.existsById(translate.getWord1().getId())){
            created = translateRepository.save(translateMapper.toEntity(translate));
        } else {
            throw new EntityNotFoundException(String.format("worCard not found (id=%d)", translate.getWord1().getId()));
        }
        return translateMapper.toDto(created);
    }

    @Override
    public Translate updateTranslate(Translate translate) {
        Optional<TranslateEntity> existing = translateRepository.findById(translate.getId());
        Translate updated = new Translate();
        if(existing.isPresent()){
            updated = translateMapper.toDto(translateRepository.save(translateMapper.toEntity(translate)));
        }
        return updated;
    }

    @Override
    public void deleteTranslateById(Long id) {
        translateRepository.deleteById(id);
    }

    @Override
    public Page<Translate> searchTranslate(TranslateSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return translateRepository.findAll(pageRequest).map(translateMapper::toDto);
    }

}
