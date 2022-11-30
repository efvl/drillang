package app.prog.evv.drillang.dto.translate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateSearchRequest {

    Long languageId;

    Long lessonId;

    String word;

    private int curNumPage;

    private int sizeOfPage;

}
