package app.prog.evv.drillang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordCardSearchRequest {

    private Long[] ids;

    private String language;

    private String word;

}
