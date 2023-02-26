package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.RefreshToken;
import app.prog.evv.drillang.entity.RefreshTokenEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.RefreshTokenMapper;
import app.prog.evv.drillang.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;
    private final RefreshTokenMapper mapper;

    public RefreshTokenServiceImpl(RefreshTokenRepository repository, RefreshTokenMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<RefreshToken> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .map(Optional::ofNullable)
                .orElseThrow(() -> new EntityNotFoundException(String.format("refresh token not found (id=%d)", id)));
    }

    @Override
    public Optional<RefreshToken> findByUserId(Long userId) {
        List<RefreshTokenEntity> list = repository.findByUserId(userId);
        if(list.size() > 0){
            return Optional.ofNullable(mapper.toDto(list.get(0)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        List<RefreshTokenEntity> list = repository.findByToken(token);
        if(list.size() > 0){
            return Optional.ofNullable(mapper.toDto(list.get(0)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RefreshToken> create(RefreshToken refreshToken) {
        RefreshTokenEntity created = repository.save(mapper.toEntity(refreshToken));
        return Optional.ofNullable(mapper.toDto(created));
    }

    @Override
    public Optional<RefreshToken> update(RefreshToken refreshToken) {
        Optional<RefreshToken> existing = findByUserId(refreshToken.getUser().getId());
        RefreshToken updated = null;
        if(existing.isPresent()){
            updated = mapper.toDto(repository.save(mapper.toEntity(refreshToken)));
        }
        return Optional.ofNullable(updated);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }

}
