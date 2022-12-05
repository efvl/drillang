package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "translate_word_lesson")
public class TranslateWordLessonEntity extends BaseUniqueEntity {

    @ManyToOne
    @JoinColumn(name = "translate_id")
    private TranslateEntity translate;

    @ManyToOne
    @JoinColumn(name = "word_lesson_id")
    private WordLessonEntity wordLesson;

    @Column(name = "target_answer")
    private int targetAnswer;

    @Column(name = "all_answer")
    private int allAnswer;

    @Column(name = "correctAnswer")
    private int correctAnswer;

    @Column(name = "count_done")
    private int countDone;

}
