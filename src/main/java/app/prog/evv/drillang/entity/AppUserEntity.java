package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUserEntity extends BaseUniqueEntity {

    @Column(name= "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "attr")
    private String attribute;

    @Column(name = "is_activated")
    private boolean isActivated;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<AppRoleEntity> roles;

    @Column(name = "date_created")
    private Instant dateCreated;

}
