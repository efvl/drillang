package app.prog.evv.drillang.service;

import app.prog.evv.drillang.auth.JwtService;
import app.prog.evv.drillang.dto.user.AppUser;
import app.prog.evv.drillang.dto.user.AuthenticationRequest;
import app.prog.evv.drillang.dto.user.AuthenticationResponse;
import app.prog.evv.drillang.dto.user.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserService userService, JwtService jwtService,
                                 AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationResponse register(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserDetails user = userService.registerUser(request);
        AppUser appUser = userService.getUserByLogin(request.getLogin());
        String jwtToken = jwtService.generateJwtToken(user);
        return new AuthenticationResponse(jwtToken, appUser);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        UserDetails user = userService.loadUserByUsername(request.getLogin());
        AppUser appUser = userService.getUserByLogin(request.getLogin());
        String jwtToken = jwtService.generateJwtToken(user);
        return new AuthenticationResponse(jwtToken, appUser);
    }

}
