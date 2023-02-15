package app.prog.evv.drillang.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long id;
    private String login;
    private String name;
    private String email;
    private String password;
    private String attr;
    private boolean isActivated;
    private Instant dateCreated;
    private Set<Role> roles;

}
