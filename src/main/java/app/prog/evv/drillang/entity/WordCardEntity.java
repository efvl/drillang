package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "word_card")
public class WordCardEntity extends BaseUniqueEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lang_id")
    private LanguageEntity language;

    @Column(name = "word")
    private String word;

    @Column(name = "transcript")
    private String transcript;

    @Column(name = "example")
    private String example;

    @Column(name = "picture_id")
    private Long pictureId;

    @Column(name = "audio_id")
    private Long audioId;

    @Column(name = "date_created")
    private Instant dateCreated;

    @ManyToMany
    @JoinTable(name="words_tags",
            joinColumns = {@JoinColumn(name="word_id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id")})
    private Set<TagEntity> tags = new HashSet<>();

}
