package app.prog.evv.drillang.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagSearchRequest {

    List<Long> ids;

    String name;

    String description;

}
