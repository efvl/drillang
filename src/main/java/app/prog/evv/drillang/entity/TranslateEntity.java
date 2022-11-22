package app.prog.evv.drillang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "translate")
public class TranslateEntity extends BaseUniqueEntity {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word1_id")
    private WordCardEntity word1;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "word2_id")
    private WordCardEntity word2;

}
