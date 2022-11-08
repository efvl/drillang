package app.prog.evv.drillang.dto;

import lombok.Data;

import java.util.List;

@Data
public class LanguageSearchRequest {

    List<Long> ids;

    String shortName;

    String fullName;

}
