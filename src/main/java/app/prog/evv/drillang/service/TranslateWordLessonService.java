package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.TranslateWLessonInfo;
import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.dto.lesson.TranslateWordLessonSearchRequest;
import app.prog.evv.drillang.dto.translate.Translate;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface TranslateWordLessonService {

    TranslateWordLesson findById(Long id);

    TranslateWordLesson createTranslateWordLesson(TranslateWordLesson translateWordLesson);

    TranslateWordLesson updateTranslateWordLesson(TranslateWordLesson translateWordLesson);

    TranslateWordLesson learnAgainTranslateWordLesson(TranslateWordLesson translateWordLesson);

    TranslateWordLesson skipTranslateWordLesson(TranslateWordLesson translateWordLesson);

    List<TranslateWLessonInfo> updateTranslateWordLessons(List<TranslateWordLesson> translateWordLessons);

    void deleteTranslateWordLessonById(Long id);

    Page<TranslateWordLesson> searchTranslateWordLessons(TranslateWordLessonSearchRequest request);

    List<TranslateWLessonInfo> getTranslatesForLesson(Long lessonId);

    void setLearnLessonAgain(Long lessonId);
}
