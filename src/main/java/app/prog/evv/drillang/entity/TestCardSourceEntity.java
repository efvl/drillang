package app.prog.evv.drillang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_card_source")
public class TestCardSourceEntity extends BaseUniqueEntity {

    @ManyToOne
    @JoinColumn(name = "test_card_id")
    private TestCardEntity testCard;

    @ManyToOne
    @JoinColumn(name = "source_info_id")
    private SourceInfoEntity sourceInfo;

    @Column(name = "time_page")
    private String timePage;

}
