package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test_lesson")
public class TestLessonEntity extends  BaseUniqueEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name="lessons_tags",
            joinColumns = {@JoinColumn(name="test_lesson_id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id")})
    private Set<LessonTagEntity> lessonTags = new HashSet<>();

    @Column(name = "reverse")
    private boolean reverse;

    @Column(name = "count_done")
    private int countDone;

}
