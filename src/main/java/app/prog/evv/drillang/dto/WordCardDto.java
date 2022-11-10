package app.prog.evv.drillang.dto;

import app.prog.evv.drillang.entity.AudioFile;
import app.prog.evv.drillang.entity.Language;
import app.prog.evv.drillang.entity.PictureFile;
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

    private PictureFileDto picture;

    private AudioFileDto audio;

    private Instant createdDate;

}
