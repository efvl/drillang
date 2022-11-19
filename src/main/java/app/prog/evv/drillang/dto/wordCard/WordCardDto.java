package app.prog.evv.drillang.dto.wordCard;

import app.prog.evv.drillang.dto.AudioFileDto;
import app.prog.evv.drillang.dto.LanguageDto;
import app.prog.evv.drillang.dto.wordPicture.PictureFileDto;
import app.prog.evv.drillang.dto.wordPicture.PictureFileInfo;
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
