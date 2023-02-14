package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_role")
public class AppRoleEntity extends  BaseUniqueEntity {

    @Column
    private String name;

    @Column(name = "attr")
    private String attribute;

}
