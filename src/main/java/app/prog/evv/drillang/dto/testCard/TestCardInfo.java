package app.prog.evv.drillang.dto.testCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCardInfo {

    private Long id;
    private String question;
    private String answer;
    private Long pictureId;
    private String codePart;

}
