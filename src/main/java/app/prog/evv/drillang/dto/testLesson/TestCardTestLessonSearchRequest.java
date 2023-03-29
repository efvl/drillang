package app.prog.evv.drillang.dto.testLesson;

import app.prog.evv.drillang.dto.lessonTag.LessonTagDto;
import app.prog.evv.drillang.dto.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCardTestLessonSearchRequest {

    private String name;
    private String question;
    private Long lessonId;
    private List<Tag> tags = new ArrayList<>();
    private List<LessonTagDto> lessonTags = new ArrayList<>();
    private int curNumPage;
    private int sizeOfPage;

}
