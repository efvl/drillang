package app.prog.evv.drillang.dto.user;

import app.prog.evv.drillang.dto.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    private Long id;
    private String login;
    private String name;
    private String email;
    private String pwd;
    private String attr;
    private boolean isActivated;
    private Instant dateCreated;
    private Set<Role> roles;

}
