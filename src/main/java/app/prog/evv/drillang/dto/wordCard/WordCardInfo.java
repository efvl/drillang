package app.prog.evv.drillang.dto.wordCard;

import app.prog.evv.drillang.dto.LanguageDto;
import app.prog.evv.drillang.dto.wordPicture.PictureFileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCardInfo {

    Long id;

    LanguageDto language;

    String word;

    Long pictureId;

}
