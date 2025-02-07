package org.reist.msvc.user.controller;

import jakarta.validation.Valid;
import org.reist.msvc.user.dto.UserDto;
import org.reist.msvc.user.dto.UsernameDto;
import org.reist.msvc.user.entity.Username;
import org.reist.msvc.user.exeptions.ResponseExceptionValid;
import org.reist.msvc.user.services.IUsernameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/username")
public class UsernameController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUsernameService service;




    @PutMapping("/update-username/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UsernameDto body, BindingResult result){
        try{
            Optional<Username> user = service.findById(id);
            if(user.isPresent()){
                if(!body.getNickname().isBlank()){
                    if (service.existsByNickname(body.getNickname()) && !Objects.equals(service.findByNickname(body.getNickname()).get().getId(), user.get().getId())){
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "El Correo Electronico ya esta registrado");
                    }
                    user.get().setNickname(body.getNickname());
                }
                if(!result.hasErrors()) {
                    user.get().setPassword(passwordEncoder.encode(body.getPassword()));
                    service.save(user.get());
                    return ResponseEntity.accepted().build();
                }
                throw ResponseExceptionValid.valid(result);
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "El usuario no existe");
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error al guardar en la base de datos", e);
        }
    }
}
