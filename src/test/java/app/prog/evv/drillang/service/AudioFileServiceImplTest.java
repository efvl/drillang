package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordAudio.AudioFileDto;
import app.prog.evv.drillang.entity.AudioFileEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.AudioFileMapper;
import app.prog.evv.drillang.repository.AudioFileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AudioFileServiceImplTest {

    @Mock
    private AudioFileRepository audioFileRepository;
    @Mock
    private AudioFileMapper audioFileMapper;
    @InjectMocks
    private AudioFileServiceImpl audioFileService;

    @Test
    void findById_existingAudio_shouldReturnDto() {
        //given
        final AudioFileEntity entity = mock(AudioFileEntity.class);
        final AudioFileDto expectedDto = mock(AudioFileDto.class);
        when(audioFileRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(audioFileMapper.toDto(eq(entity))).thenReturn(expectedDto);

        //when
        AudioFileDto actualDto = audioFileService.findById(1L);

        //then
        verify(audioFileRepository).findById(1L);
        verify(audioFileMapper).toDto(entity);
        Assertions.assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    void findById_notExpectingEntity_shouldThrowException(){
        //given
        when(audioFileRepository.findById(100L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThatThrownBy(() -> audioFileService.findById(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("audio")
                .hasMessageContaining("id=100");
    }

}