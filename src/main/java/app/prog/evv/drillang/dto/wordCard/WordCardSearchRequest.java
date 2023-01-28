package app.prog.evv.drillang.dto.wordCard;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import app.prog.evv.drillang.dto.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCardSearchRequest {

    private Long[] ids;

    private LanguageDto language;

    private Long languageId;

    private String word;

    private List<Tag> tags = new ArrayList<>();

    private int curNumPage;

    private int sizeOfPage;

}
