package org.reist.msvc.user.auth;

import lombok.RequiredArgsConstructor;
import org.reist.msvc.user.dto.UsernameDto;
import org.reist.msvc.user.entity.User;
import org.reist.msvc.user.entity.Username;
import org.reist.msvc.user.repositories.IUserRepository;
import org.reist.msvc.user.repositories.IUsernameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final IUsernameRepository usernameDao;
    private final IUserRepository userDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var username = new Username(request.getNickname(),
                                passwordEncoder.encode(request.getPassword()),
                                request.getSessionLog() );
        usernameDao.save(username);
        userDao.save(new User(username));
        var jwt = jwtService.generateToken(username);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNickname(),
                        request.getPassword()
                )
        );
        var user = usernameDao.findByNickname(request.getNickname())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public boolean isPresentUsername(String nickname){
        return usernameDao.existsByNickname(nickname);
    }

}
