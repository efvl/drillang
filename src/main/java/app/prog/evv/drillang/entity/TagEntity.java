package app.prog.evv.drillang.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
public class TagEntity extends BaseUniqueEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
