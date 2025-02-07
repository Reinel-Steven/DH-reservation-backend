package org.reist.msvc.user.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class ResponseExceptionValid {

    public static ResponseStatusException valid(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "Error: " + err.getField() + " Message: " + err.getDefaultMessage());
        });
        return new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.toString());
    }
}
