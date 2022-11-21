package app.prog.evv.drillang.dto.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSearchRequest {

    List<Long> ids;

    String shortName;

    String fullName;

}
