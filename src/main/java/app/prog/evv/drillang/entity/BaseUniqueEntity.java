package app.prog.evv.drillang.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class BaseUniqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

}
