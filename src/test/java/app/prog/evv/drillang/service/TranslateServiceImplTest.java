package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.translate.Translate;
import app.prog.evv.drillang.entity.TranslateEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.TranslateMapper;
import app.prog.evv.drillang.repository.TranslateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TranslateServiceImplTest {

    @Mock private TranslateRepository translateRepository;

    @Mock private TranslateMapper translateMapper;

    @InjectMocks
    private TranslateServiceImpl translateService;

    @Test
    void findById_existingEntity_shouldReturnDto() {
        //given
        TranslateEntity entity = mock(TranslateEntity.class);
        Translate expectedDto = mock(Translate.class);
        when(translateRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(translateMapper.toDto(entity)).thenReturn(expectedDto);

        //when
        Translate actualDto = translateService.findById(1L);

        //then
        verify(translateRepository).findById(1L);
        verify(translateMapper).toDto(eq(entity));
        Assertions.assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    void findById_notExistingEntity_shouldThrowException(){
        //given
        when(translateRepository.findById(100L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThatThrownBy(() -> translateService.findById(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("translate")
                .hasMessageContaining("id=100");
    }
}