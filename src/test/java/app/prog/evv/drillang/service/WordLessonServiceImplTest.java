package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lesson.WordLesson;
import app.prog.evv.drillang.entity.WordLessonEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.WordLessonMapper;
import app.prog.evv.drillang.repository.WordLessonRepository;
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
class WordLessonServiceImplTest {

    @Mock
    private WordLessonRepository wordLessonRepository;

    @Mock
    private WordLessonMapper mapper;

    @InjectMocks
    private WordLessonServiceImpl wordLessonService;

    @Test
    void findById_existingEntity_returnDto() {
        //given
        WordLessonEntity entity = mock(WordLessonEntity.class);
        WordLesson expectedDto = mock(WordLesson.class);
        when(wordLessonRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(mapper.toDto(entity)).thenReturn(expectedDto);

        //when
        WordLesson actualDto = wordLessonService.findById(1L);

        //then
        verify(wordLessonRepository).findById(1L);
        verify(mapper).toDto(eq(entity));
        Assertions.assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    void findById_notExistingEntity_shouldThrowException() {
        //given
        when(wordLessonRepository.findById(100L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThatThrownBy(() -> wordLessonService.findById(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("word lesson")
                .hasMessageContaining("id=100");
    }

}