package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.user.RefreshToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByUserId(Long userId);

    Optional<RefreshToken> create(RefreshToken refreshToken);

    Optional<RefreshToken> update(RefreshToken refreshToken);

    void deleteById(Long id);

    void deleteByUserId(Long userId);

}
