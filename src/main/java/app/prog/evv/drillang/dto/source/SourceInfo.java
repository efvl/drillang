package app.prog.evv.drillang.dto.source;

import app.prog.evv.drillang.entity.SourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceInfo {

    private Long id;
    private SourceType sourceType;
    private String pathLink;
    private String name;
    private String authors;
    private String other;

}
