package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lang.LanguageDto;
import app.prog.evv.drillang.entity.LanguageEntity;
import app.prog.evv.drillang.mapper.LangMapper;
import app.prog.evv.drillang.repository.LangRepository;
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
class LangServiceImplTest {

    @Mock
    private LangRepository langRepository;
    @Mock
    private LangMapper langMapper;
    @InjectMocks
    private LangServiceImpl langService;

    @Test
    void findById_existingLang_shouldReturnDto() {
        //given
        final LanguageEntity entity = mock(LanguageEntity.class);
        final LanguageDto expectDto = mock(LanguageDto.class);
        when(langRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(langMapper.toDto(eq(entity))).thenReturn(expectDto);

        //when
        LanguageDto actualDto = langService.findById(1L);

        //then
        verify(langRepository).findById(1L);
        verify(langMapper).toDto(entity);
        Assertions.assertThat(actualDto).isEqualTo(expectDto);
    }

}