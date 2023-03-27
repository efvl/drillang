package app.prog.evv.drillang.dto.lessonTag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonTagSearchRequest {

    List<Long> ids;

    String name;

    String description;

}
