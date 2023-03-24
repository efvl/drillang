package app.prog.evv.drillang.dto.testCard;

import app.prog.evv.drillang.dto.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCardSearchRequest {

    private String question;
    private String answer;
    private List<Tag> tags = new ArrayList<>();
    private int curNumPage;
    private int sizeOfPage;

}
