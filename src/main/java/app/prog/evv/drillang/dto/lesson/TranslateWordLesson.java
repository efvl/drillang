package app.prog.evv.drillang.dto.lesson;

import app.prog.evv.drillang.dto.translate.Translate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateWordLesson {

    private Long id;

    private Translate translate;

    private WordLesson wordLesson;

    private int targetAnswer;

    private int allAnswer;

    private int correctAnswer;

}
