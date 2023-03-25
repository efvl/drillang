package app.prog.evv.drillang.dto.testCard;

import app.prog.evv.drillang.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCardDto {

    private Long id;
    private String question;
    private String answer;
    private Long pictureId;
    private String codePart;
    private Set<TestCardSourceDto> sources;
    private Instant dateCreated;
    private Set<TagEntity> tags;

}
