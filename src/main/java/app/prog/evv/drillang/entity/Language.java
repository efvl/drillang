package app.prog.evv.drillang.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "language")
public class Language extends BaseUniqueEntity{

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

}
