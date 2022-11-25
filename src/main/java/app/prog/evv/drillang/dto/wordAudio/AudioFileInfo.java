package app.prog.evv.drillang.dto.wordAudio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioFileInfo {

    Long id;

    String fileName;

    String checksum;

}
