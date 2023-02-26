package app.prog.evv.drillang.service;

import app.prog.evv.drillang.auth.JwtService;
import app.prog.evv.drillang.dto.user.*;
import app.prog.evv.drillang.exception.auth.TokenExpiredException;
import app.prog.evv.drillang.exception.auth.TokenValidationException;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserService userService, RefreshTokenService refreshTokenService, JwtService jwtService,
                                 AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationResponse register(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.registerUser(request);
        AppUser appUser = userService.getUserByLogin(request.getLogin());
        return new AuthenticationResponse(appUser, "", "");
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        UserDetails user = userService.loadUserByUsername(request.getLogin());
        String accessToken = jwtService.generateAccessToken(new HashMap<>(), user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        AppUser appUser = userService.getUserByLogin(request.getLogin());
        RefreshToken tokenFromDb = refreshTokenService.findByUserId(appUser.getId()).orElse(new RefreshToken());
        tokenFromDb.setToken(refreshToken);
        tokenFromDb.setUser(appUser);
        refreshTokenService.create(tokenFromDb);

        return new AuthenticationResponse(appUser, accessToken, refreshToken);
    }

    public void logout(AuthenticationRequest request) {
        AppUser appUser = userService.getUserByLogin(request.getLogin());
        Optional<RefreshToken> tokenFromDb = refreshTokenService.findByUserId(appUser.getId());
        if(tokenFromDb.isPresent()){
            tokenFromDb.get().setToken("");
            refreshTokenService.update(tokenFromDb.get());
        }
    }

    public AuthenticationResponse refresh(RefreshToken request) {
        RefreshToken tokenFromDb = refreshTokenService.findByToken(request.getToken())
                .orElseThrow(() -> new EntityNotFoundException("refresh token not found"));
        UserDetails userDetails = userService.loadUserByUsername(request.getUser().getLogin());

        if (jwtService.isTokenValid(tokenFromDb.getToken(), userDetails)) {
            String accessToken = jwtService.generateAccessToken(new HashMap<>(), userDetails);
            return new AuthenticationResponse(request.getUser(), accessToken, "");
        } else {
            throw new TokenValidationException(String.format("refresh token is not valid (login=%s)", request.getUser().getLogin()));
        }

    }
}
