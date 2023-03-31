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
    private Long pictureId;
    private Long card1Id;
    private String word1;
    private String example1;
    private Long audioId1;
    private Long card2Id;
    private String word2;
    private String example2;
    private Long audioId2;
    private Long lessonId;
    private int targetAnswer;
    private int allAnswer;
    private int correctAnswer;
    private int countDone;
    private boolean skip;

}
