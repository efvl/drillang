package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.tag.Tag;
import app.prog.evv.drillang.entity.TagEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TagMapperImpl;
import app.prog.evv.drillang.repository.TagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagMapperImpl tagMapper;
    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    void getById_existingTag_shouldReturnDto() {
        //given
        TagEntity tagEntity = new TagEntity("name", "description");
        Tag expectDto = new Tag(1L, "name", "description");
        when(tagRepository.findById(1L)).thenReturn(Optional.ofNullable(tagEntity));
        when(tagMapper.toDto(eq(tagEntity))).thenReturn(expectDto);

        //when
        Tag actualDto = tagService.getById(1L);

        //then
        verify(tagRepository).findById(1L);
        verify(tagMapper).toDto(tagEntity);
        Assertions.assertThat(actualDto).isEqualTo(expectDto);
    }

    @Test
    void getById_notExistingTag_shouldThrowException() {
        //given
        when(tagRepository.findById(100L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThatThrownBy(() -> tagService.getById(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("tag")
                .hasMessageContaining("id=100");
    }

}