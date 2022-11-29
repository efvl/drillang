package app.prog.evv.drillang.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateWLessonInfo {

    private Long id;

    private Long translateId;

    private String word1;

    private String word2;

    private Long lessonId;

    private int targetAnswer;

    private int allAnswer;

    private int correctAnswer;

}
