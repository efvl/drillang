package app.prog.evv.drillang.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {

    List<Long> ids;

    String name;

    String attr;

}
