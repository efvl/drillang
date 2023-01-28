package app.prog.evv.drillang.dto.translate;

import app.prog.evv.drillang.dto.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslateSearchRequest {

    Long languageId;

    Long lessonId;

    String word;

    private int curNumPage;

    private int sizeOfPage;

    private List<Tag> tags = new ArrayList<>();

}
