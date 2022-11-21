package app.prog.evv.drillang.dto.translate;

import app.prog.evv.drillang.dto.wordCard.WordCardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Translate {

    Long id;

    WordCardDto word1;

    WordCardDto word2;

}
