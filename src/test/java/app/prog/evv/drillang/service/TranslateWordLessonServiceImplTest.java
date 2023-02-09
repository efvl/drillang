package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.TranslateWordLesson;
import app.prog.evv.drillang.entity.TranslateWordLessonEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TranslateWordLessonMapper;
import app.prog.evv.drillang.repository.TranslateWordLessonRepository;
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
class TranslateWordLessonServiceImplTest {

    @Mock
    private TranslateWordLessonRepository repository;

    @Mock
    private TranslateWordLessonMapper mapper;

    @InjectMocks
    private TranslateWordLessonServiceImpl translateWordLessonService;

    @Test
    void findById_existingEntity_shouldReturnDto() {
        //given
        TranslateWordLessonEntity entity = mock(TranslateWordLessonEntity.class);
        TranslateWordLesson expectedDto = mock(TranslateWordLesson.class);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(mapper.toDto(entity)).thenReturn(expectedDto);

        //when
        TranslateWordLesson actualDto = translateWordLessonService.findById(1L);

        //then
        verify(repository).findById(1L);
        verify(mapper).toDto(eq(entity));
        Assertions.assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    void findById_notExistingEntity_shouldThrowException(){
        //given
        when(repository.findById(100L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThatThrownBy(() -> translateWordLessonService.findById(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("translate word lesson")
                .hasMessageContaining("id=100");
    }
}