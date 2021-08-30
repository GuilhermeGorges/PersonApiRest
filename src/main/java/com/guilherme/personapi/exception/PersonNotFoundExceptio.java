package com.guilherme.personapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExceptio extends Exception {

    public PersonNotFoundExceptio(Long id) {
        super("Person not found with ID " + id);
    }
}
