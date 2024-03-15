package com.gleisonoliveira.personapi.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gleisonoliveira.personapi.Data.VO.V1.PersonVO;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Services.Person.PersonService;
import com.gleisonoliveira.personapi.Util.MediaType;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public List<PersonVO> list() {
        return personService.list();
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML }, consumes = {
                    MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML }, consumes = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML })
    public PersonVO update(@PathVariable(value = "id") Long id, @RequestBody PersonVO person)
            throws ResourceNotFoundException {
        return personService.update(id, person);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        personService.delete(id);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    public PersonVO get(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return personService.get(id);
    }

}
