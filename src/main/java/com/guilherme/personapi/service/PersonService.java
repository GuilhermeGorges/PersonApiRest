package com.guilherme.personapi.service;

import com.guilherme.personapi.dto.request.PersonDTO;
import com.guilherme.personapi.dto.response.MessageResponseDTO;
import com.guilherme.personapi.entity.Person;
import com.guilherme.personapi.exception.PersonNotFoundExceptio;
import com.guilherme.personapi.mapper.PersonMapper;
import com.guilherme.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID " );
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExceptio {
        Person person = verityIfExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundExceptio {
        verityIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundExceptio {
        verityIfExists(id);

        Person personToSave = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToSave);
        return createMessageResponse(updatedPerson.getId(),"Updated person with ID " );

    }

    private Person verityIfExists(Long id) throws PersonNotFoundExceptio {
        return personRepository.findById(id)
                .orElseThrow(() ->  new PersonNotFoundExceptio(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
