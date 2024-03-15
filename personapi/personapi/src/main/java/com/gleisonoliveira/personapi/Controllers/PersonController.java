package com.gleisonoliveira.personapi.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gleisonoliveira.personapi.Data.VO.V1.PersonVO;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Services.Person.PersonService;
import com.gleisonoliveira.personapi.Util.MediaType;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public List<PersonVO> list() {
        var persons = personService.list();

        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).list())
                        .slash(p.getId())
                        .withSelfRel()));

        return persons;
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML }, consumes = {
                    MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public PersonVO create(@RequestBody PersonVO person) {
        var vo = personService.create(person);

        vo.add(linkTo(methodOn(PersonController.class)
                .create(vo))
                .slash(vo.getId())
                .withSelfRel());
        vo.add(linkTo(methodOn(PersonController.class)
                .list()).withRel(IanaLinkRelations.COLLECTION));

        return vo;
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML }, consumes = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML })
    public PersonVO update(@PathVariable(value = "id") Long id, @RequestBody PersonVO person)
            throws ResourceNotFoundException {
        var vo = personService.update(id, person);

        vo.add(linkTo(methodOn(PersonController.class)
                .update(id, vo))
                .withSelfRel());

        vo.add(linkTo(methodOn(PersonController.class)
                .list()).withRel(IanaLinkRelations.COLLECTION));

        return vo;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        personService.delete(id);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    public PersonVO get(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        var vo = personService.get(id);
        
        vo.add(linkTo(methodOn(PersonController.class).get(id)).withSelfRel());

        vo.add(linkTo(methodOn(PersonController.class)
                .list()).withRel(IanaLinkRelations.COLLECTION));

        return vo;
    }

}
