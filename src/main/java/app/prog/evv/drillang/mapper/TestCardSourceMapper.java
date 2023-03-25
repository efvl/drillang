package app.prog.evv.drillang.mapper;

import app.prog.evv.drillang.dto.testCard.TCardSourceInfo;
import app.prog.evv.drillang.dto.testCard.TestCardSourceDto;
import app.prog.evv.drillang.entity.TestCardSourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PictureFileMapper.class, TagMapper.class, SourceInfoMapper.class})
public interface TestCardSourceMapper {

    @Mapping(target = "testCard", ignore = true)
    TestCardSourceDto toDto(TestCardSourceEntity entity);

    @Mapping(target= "testCardId", source = "testCard.id")
    TCardSourceInfo toSourceInfo(TestCardSourceEntity entity);

    @Mapping(target = "testCard", ignore = true)
    TestCardSourceEntity toEntity(TestCardSourceDto dto);

}
