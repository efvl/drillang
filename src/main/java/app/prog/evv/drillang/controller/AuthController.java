package app.prog.evv.drillang.controller;

import app.prog.evv.drillang.dto.user.*;
import app.prog.evv.drillang.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/auth")
@CrossOrigin
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(description = "registration new user")
    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> registration(@RequestBody RegisterRequest request){
        return ResponseEntity.ok().body(authenticationService.register(request));
    }

    @Operation(description = "user login")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logIn(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok().body(authenticationService.login(request));
    }

    @Operation(description = "user logout")
    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestBody AuthenticationRequest request){
        authenticationService.logout(request);
        return ResponseEntity.ok().body("logout is done");
    }

    @Operation(description = "get new refreshToken")
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshToken request){
        return ResponseEntity.ok().body(authenticationService.refresh(request));
    }

}
