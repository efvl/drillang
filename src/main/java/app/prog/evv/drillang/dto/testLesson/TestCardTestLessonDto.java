package app.prog.evv.drillang.dto.testLesson;

import app.prog.evv.drillang.entity.TestCardEntity;
import app.prog.evv.drillang.entity.TestLessonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCardTestLessonDto {

    private Long id;
    private TestCardEntity testCard;
    private TestLessonEntity testLesson;
    private int targetAnswer;
    private int allAnswer;
    private int correctAnswer;
    private int countDone;
    private boolean skip;

}
