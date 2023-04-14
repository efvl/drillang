package app.prog.evv.drillang.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateWordLessonSearchRequest {

    private Long translateId;

    private Long wordLessonId;

    private List<Long> ids;

    private int curNumPage;

    private int sizeOfPage;

}
