package app.prog.evv.drillang.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    private Long id;
    private AppUser user;
    private String token;

}
