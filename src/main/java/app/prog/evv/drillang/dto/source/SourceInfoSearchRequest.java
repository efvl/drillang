package app.prog.evv.drillang.dto.source;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import app.prog.evv.drillang.dto.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceInfoSearchRequest {

    private String name;

    private String authors;

    private List<Tag> tags = new ArrayList<>();

    private int curNumPage;

    private int sizeOfPage;

}
