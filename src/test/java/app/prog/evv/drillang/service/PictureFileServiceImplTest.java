package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordPicture.PictureFileDto;
import app.prog.evv.drillang.entity.PictureFileEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.PictureFileMapper;
import app.prog.evv.drillang.repository.PictureFileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PictureFileServiceImplTest {

    @Mock
    private PictureFileRepository pictureFileRepository;

    @Mock
    private PictureFileMapper pictureFileMapper;

    @InjectMocks
    private PictureFileServiceImpl pictureFileService;

    @Test
    void findById_existingEntity_shouldReturnDto() {
        //given
        PictureFileEntity entity = mock(PictureFileEntity.class);
        PictureFileDto expectedDto = mock(PictureFileDto.class);
        when(pictureFileRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(pictureFileMapper.toDto(entity)).thenReturn(expectedDto);

        //when
        PictureFileDto actualDto = pictureFileService.findById(1L);

        //then
        verify(pictureFileRepository).findById(1L);
        verify(pictureFileMapper).toDto(entity);
        Assertions.assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    void findById_notExistingEntity_shouldThrowException() {
        //given
        when(pictureFileRepository.findById(100L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThatThrownBy(() -> pictureFileService.findById(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("picture")
                .hasMessageContaining("id=100");
    }


}
