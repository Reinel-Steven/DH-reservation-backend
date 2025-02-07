package org.reist.msvc.user.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest body){
        try {
            if(!authenticationService.isPresentUsername(body.getNickname())){
                return ResponseEntity.ok(authenticationService.register(body));
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "El nombre de usuario ya esta registrado");
            }
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error al guardar en la base de datos", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest body){
        return ResponseEntity.ok(authenticationService.login(body));
    }
}
