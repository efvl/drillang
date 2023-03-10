package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.Role;
import app.prog.evv.drillang.entity.AppRoleEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.AppRoleMapper;
import app.prog.evv.drillang.repository.AppRoleRepository;
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
class AppRoleServiceImplTest {

    @Mock
    private AppRoleRepository repository;
    @Mock
    private AppRoleMapper mapper;
    @InjectMocks
    private AppRoleServiceImpl appRoleService;

    @Test
    void getById_existingLang_shouldReturnDto() {
        //given
        final AppRoleEntity entity = mock(AppRoleEntity.class);
        final Role expectDto = mock(Role.class);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(mapper.toDto(eq(entity))).thenReturn(expectDto);

        //when
        Role actualDto = appRoleService.getById(1L);

        //then
        verify(repository).findById(1L);
        verify(mapper).toDto(entity);
        Assertions.assertThat(actualDto).isEqualTo(expectDto);
    }

    @Test
    void getById_notExistingEntity_shouldThrowException() {
        //given
        when(repository.findById(100L)).thenReturn(Optional.empty());

        //when
        //then
        Assertions.assertThatThrownBy(() -> appRoleService.getById(100L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("app role")
                .hasMessageContaining("id=100");
    }
}