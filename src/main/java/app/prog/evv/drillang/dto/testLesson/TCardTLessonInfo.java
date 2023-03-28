package app.prog.evv.drillang.dto.testLesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TCardTLessonInfo {

    private Long id;

    private Long testCardId;
    private String question;
    private String answer;
    private Long pictureId;
    private String codePart;

    private Long lessonId;
    private int targetAnswer;
    private int allAnswer;
    private int correctAnswer;
    private int countDone;
    private boolean skip;

}
