package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_card_test_lesson")
public class TestCardTestLessonEntity extends BaseUniqueEntity {

        @ManyToOne
        @JoinColumn(name = "test_card_id")
        private TestCardEntity testCard;

        @ManyToOne
        @JoinColumn(name = "test_lesson_id")
        private TestLessonEntity testLesson;

        @Column(name = "target_answer")
        private int targetAnswer;

        @Column(name = "all_answer")
        private int allAnswer;

        @Column(name = "correct_answer")
        private int correctAnswer;

        @Column(name = "count_done")
        private int countDone;

        @Column(name = "skip")
        private boolean skip;

}
