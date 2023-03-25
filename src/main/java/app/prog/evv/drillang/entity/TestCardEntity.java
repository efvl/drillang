package app.prog.evv.drillang.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_card")
public class TestCardEntity extends BaseUniqueEntity {

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "testCard", cascade = {
            CascadeType.MERGE
    })
    private Set<TestCardSourceEntity> sources = new HashSet<>();

    @Column(name = "picture_id")
    private Long pictureId;

    @Column(name = "code_part")
    private String codePart;

    @Column(name = "date_created")
    private Instant dateCreated;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name="tests_tags",
            joinColumns = {@JoinColumn(name="test_id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id")})
    private Set<TagEntity> tags = new HashSet<>();

}
