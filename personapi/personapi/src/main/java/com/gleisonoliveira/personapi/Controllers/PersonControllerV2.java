package com.gleisonoliveira.personapi.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.gleisonoliveira.personapi.Data.VO.V2.PersonVOV2;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Services.Person.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/person/v2")
public class PersonControllerV2 {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVOV2> list() {
        return personService.listV2();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 create(@RequestBody PersonVOV2 person) {
        return personService.createV2(person);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 update(@PathVariable(value = "id") Long id, @RequestBody PersonVOV2 person)
            throws ResourceNotFoundException {
        return personService.updateV2(id, person);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        personService.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 get(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return personService.getV2(id);
    }

}
