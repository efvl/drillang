package app.prog.evv.drillang.dto.wordPicture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureFileInfo {

    Long id;

    String fileName;

    String checksum;

}
