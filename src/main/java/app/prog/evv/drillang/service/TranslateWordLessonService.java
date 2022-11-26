package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.dto.lesson.TranslateWordLessonSearchRequest;
import org.springframework.data.domain.Page;

public interface TranslateWordLessonService {

    TranslateWordLesson findById(Long id);

    TranslateWordLesson createTranslateWordLesson(TranslateWordLesson translateWordLesson);

    TranslateWordLesson updateTranslateWordLesson(TranslateWordLesson translateWordLesson);

    void deleteTranslateWordLessonById(Long id);

    Page<TranslateWordLesson> searchTranslateWordLessons(TranslateWordLessonSearchRequest request);

}
