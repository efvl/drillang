package app.prog.evv.drillang.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long id;
    private String name;
    private String attr;

}
