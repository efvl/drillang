package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.testCard.TestCardDto;
import app.prog.evv.drillang.dto.testCard.TestCardInfo;
import app.prog.evv.drillang.entity.TestCardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PictureFileMapper.class, TagMapper.class})
public interface TestCardMapper {

    TestCardDto toDto(TestCardEntity entity);

    TestCardInfo toWordCardInfo(TestCardEntity entity);

    TestCardEntity toEntity(TestCardDto dto);
}
