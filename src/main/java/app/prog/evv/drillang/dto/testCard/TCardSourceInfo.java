package app.prog.evv.drillang.dto.testCard;

import app.prog.evv.drillang.dto.source.SourceInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TCardSourceInfo {

    private Long testCardId;
    private SourceInfoDto sourceInfo;
    private String timePage;

}
