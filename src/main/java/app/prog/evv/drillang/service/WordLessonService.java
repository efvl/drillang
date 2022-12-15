package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.WordLesson;
import app.prog.evv.drillang.dto.lesson.WordLessonSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WordLessonService {

    WordLesson findById(Long id);

    WordLesson createWordLesson(WordLesson wordLesson);

    WordLesson updateWordLesson(WordLesson wordLesson);

    void deleteWordLessonById(Long id);

    Page<WordLesson> searchWordLessons(WordLessonSearchRequest request);

    List<WordLesson> getLessonsByFromLanguage(Long fromLangId);
}
