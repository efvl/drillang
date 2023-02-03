package app.prog.evv.drillang.dto.lang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    private Long id;

    private String shortName;

    private String fullName;

}
