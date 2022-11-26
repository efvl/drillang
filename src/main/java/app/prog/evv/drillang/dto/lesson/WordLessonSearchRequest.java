package app.prog.evv.drillang.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordLessonSearchRequest {

    private String name;

    private Long fromLanguage;

    private Long toLanguage;

    private int curNumPage;

    private int sizeOfPage;

}
