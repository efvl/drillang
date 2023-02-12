package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.wordCard.WordCardDto;
import app.prog.evv.drillang.entity.WordCardEntity;
import app.prog.evv.drillang.mapper.WordCardMapper;
import app.prog.evv.drillang.repository.WordCardRepository;
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
class WordCardServiceImplTest {

    @Mock
    private WordCardRepository wordCardRepository;

    @Mock
    private WordCardMapper mapper;

    @InjectMocks
    private WordCardServiceImpl wordCardService;


    @Test
    void findById_existingEntity_returnDto() {
        //given
        WordCardEntity entity = mock(WordCardEntity.class);
        WordCardDto expectedDto = mock(WordCardDto.class);
        when(wordCardRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(mapper.toDto(entity)).thenReturn(expectedDto);

        //when
        WordCardDto actualDto = wordCardService.findById(1L);

        //then
        verify(wordCardRepository).findById(1L);
        verify(mapper).toDto(eq(entity));
        Assertions.assertThat(actualDto).isEqualTo(actualDto);
    }


}