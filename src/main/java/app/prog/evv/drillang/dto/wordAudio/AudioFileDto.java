package app.prog.evv.drillang.dto.wordAudio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioFileDto {

    private Long id;

    private byte[] content;

    private String fileName;

    private Long size;

    private String contentType;

    private Instant createdDate;

}
