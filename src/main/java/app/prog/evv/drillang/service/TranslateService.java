package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.translate.Translate;
import app.prog.evv.drillang.dto.translate.TranslateSearchRequest;
import org.springframework.data.domain.Page;

public interface TranslateService {

    Translate findById(Long id);

    Translate createTranslate(Translate translate);

    Translate updateTranslate(Translate translate);

    void deleteTranslateById(Long id);

    Page<Translate> searchTranslate(TranslateSearchRequest request);

    Page<Translate> searchTranslateForLesson(TranslateSearchRequest request);

}
