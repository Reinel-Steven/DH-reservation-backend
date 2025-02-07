package org.reist.msvc.user.controller;

import org.reist.msvc.user.dto.UserDto;
import org.reist.msvc.user.entity.User;
import org.reist.msvc.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final static String ERROR_DB = "Error connection data base";
    private final static String USER_NOT_EXIST = "El producto no existe";

    @Autowired
    private IUserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers(){
        try {
            List<UserDto> users = new ArrayList<UserDto>();
            for(User user: service.findAll() ){
                users.add(new UserDto(user));
            }
            if(!users.isEmpty()) {
                return ResponseEntity.ok(users);
            }
            return ResponseEntity.noContent().build();
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userDetail(@PathVariable Long id){
        try {
            Optional<User> user = service.findById(id);
            if(user.isPresent()){
                return ResponseEntity.ok(new UserDto(user.get()));
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, USER_NOT_EXIST);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> userByEmail(@PathVariable String email) {
        try{
            Optional<User> user = service.findByEmail(email);
            if(user.isPresent()){
                return ResponseEntity.ok(new UserDto(user.get()));
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, USER_NOT_EXIST);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }



    @PutMapping("/update-user")
    public ResponseEntity<UserDto> update(@RequestBody UserDto body){
        try{
            Optional<User> user = service.findByUsername(body.getUsername());
            if(user.isPresent()){
                if(body.getName()!=null)user.get().setName(body.getName());
                if(body.getLastName()!=null)user.get().setLastName(body.getLastName());
                if(body.getAddress()!=null)user.get().setAddress(body.getAddress());
                if(body.getPhone()!=null)user.get().setPhone(body.getPhone());
                if(body.getPhoto()!=null)user.get().setPhoto(body.getPhoto());
                if(body.getEmail()!=null)user.get().setEmail(body.getEmail());
                if(body.getIdentification()!=null)user.get().setIdentification(body.getIdentification());
                service.save(user.get());
                return ResponseEntity.accepted().build();
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, USER_NOT_EXIST);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        try {
            Optional<User> user = service.findById(id);
            if(user.isPresent()){
                service.delete(id);
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, USER_NOT_EXIST);
        }catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }

    @GetMapping("/users-by-ids")
    public ResponseEntity<List<UserDto>> usersCourse(@RequestParam List<Long> ids){
        try {
            if (!service.findByIds(ids).isEmpty()){
                List<UserDto> list = new ArrayList<>();
                for (User user : service.findByIds(ids)){
                    list.add(new UserDto(user));
                }
                return ResponseEntity.ok(list);
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, USER_NOT_EXIST);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }
}
