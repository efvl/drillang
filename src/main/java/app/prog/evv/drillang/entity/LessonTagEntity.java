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
@Table(name = "lesson_tag")
public class LessonTagEntity extends BaseUniqueEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
