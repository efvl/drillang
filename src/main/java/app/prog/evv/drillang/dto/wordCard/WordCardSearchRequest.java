package app.prog.evv.drillang.dto.wordCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCardSearchRequest {

    private Long[] ids;

    private String language;

    private String word;

    private int curNumPage;

    private int sizeOfPage;

}
