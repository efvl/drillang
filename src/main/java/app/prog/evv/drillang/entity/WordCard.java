package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "word_card")
public class WordCard extends BaseUniqueEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "word")
    private String word;

    @Column(name = "transcript")
    private String transcript;

    @Column(name = "example")
    private String example;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picture_id")
    private PictureFile picture;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audio_id")
    private AudioFile audio;

    @Column(name = "created_date")
    private Instant createdDate;

}
