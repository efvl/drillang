package app.prog.evv.drillang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    private Long id;

    private String shortName;

    private String fullName;

}
