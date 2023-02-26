package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshTokenEntity extends BaseUniqueEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    AppUserEntity user;

    @Column(name = "refresh_token")
    String refreshToken;

}
