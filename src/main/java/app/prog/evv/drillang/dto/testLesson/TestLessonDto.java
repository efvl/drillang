package app.prog.evv.drillang.dto.testLesson;

import app.prog.evv.drillang.entity.LessonTagEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestLessonDto {

    private Long id;
    private String name;
    private Set<LessonTagEntity> lessonTags = new HashSet<>();
    private boolean reverse;
    private int countDone;

}
