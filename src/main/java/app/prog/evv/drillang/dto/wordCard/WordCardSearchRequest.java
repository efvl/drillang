package app.prog.evv.drillang.dto.wordCard;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCardSearchRequest {

    private Long[] ids;

    private LanguageDto language;

    private Long languageId;

    private String word;

    private int curNumPage;

    private int sizeOfPage;

}
