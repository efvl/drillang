package app.prog.evv.drillang.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "language")
public class Language extends BaseUniqueEntity{

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

}
