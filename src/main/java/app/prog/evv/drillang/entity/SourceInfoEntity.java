package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "source_info")
public class SourceInfoEntity extends BaseUniqueEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @Column(name = "path_link")
    private String pathLink;

    @Column(name = "name")
    private String name;

    @Column(name = "authors")
    private String authors;

    @Column(name = "other")
    private String other;

}
