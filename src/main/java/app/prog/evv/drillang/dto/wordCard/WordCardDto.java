package app.prog.evv.drillang.dto.wordCard;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCardDto {

    private Long id;

    private LanguageDto language;

    private String word;

    private String transcript;

    private String example;

    private Long pictureId;

    private Long audioId;

    private Instant dateCreated;

}
