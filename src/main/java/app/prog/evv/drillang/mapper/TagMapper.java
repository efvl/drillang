package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.tag.Tag;
import app.prog.evv.drillang.entity.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagEntity toEntity(Tag tag);

    Tag toDto(TagEntity entity);

}
