package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "word_lesson")
public class WordLessonEntity extends BaseUniqueEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_lang_id")
    private LanguageEntity fromLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_lang_id")
    private LanguageEntity toLanguage;

    @Column(name = "reverse")
    private boolean reverse;

    @Column(name = "count_done")
    private int countDone;

    @Column(name = "count_chars")
    private int countChars;

}
