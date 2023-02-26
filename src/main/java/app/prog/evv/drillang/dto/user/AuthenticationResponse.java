package app.prog.evv.drillang.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private AppUser user;

    private String accessToken;

    private String refreshToken;

}
