package app.prog.evv.drillang.dto.testCard;

import app.prog.evv.drillang.dto.source.SourceInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCardSourceDto {

    private Long id;
    private TestCardDto testCard;
    private SourceInfoDto sourceInfo;
    private String timePage;

}
