package app.prog.evv.drillang.dto.lesson;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordLesson {

    private Long id;

    private String name;

    private LanguageDto fromLanguage;

    private LanguageDto toLanguage;

    private Long translatesCount;

    private boolean reverse;

    private int countDone;

    private int countChars;

    private boolean useOrder;

}
