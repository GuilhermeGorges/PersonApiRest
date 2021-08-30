package com.guilherme.personapi.controller;

import com.guilherme.personapi.dto.request.PersonDTO;
import com.guilherme.personapi.dto.response.MessageResponseDTO;
import com.guilherme.personapi.exception.PersonNotFoundExceptio;
import com.guilherme.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@Valid @RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> lsitAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundExceptio {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundExceptio {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundExceptio {
        personService.delete(id);
    }

}
