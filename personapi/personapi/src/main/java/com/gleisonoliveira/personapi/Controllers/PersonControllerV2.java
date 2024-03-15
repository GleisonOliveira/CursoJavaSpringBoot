package com.gleisonoliveira.personapi.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.gleisonoliveira.personapi.Data.VO.V2.PersonVOV2;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Services.Person.PersonService;
import com.gleisonoliveira.personapi.Util.MediaType;

@RestController
@RequestMapping("/person/v2")
public class PersonControllerV2 {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public CollectionModel<PersonVOV2> list() {
        var persons = personService.listV2();

        persons.stream()
                .forEach(
                        p -> p.add(linkTo(methodOn(PersonControllerV2.class)
                                .list())
                                .slash(p.getId())
                                .withSelfRel()));

        CollectionModel<PersonVOV2> collectionModel = CollectionModel.of(persons);

        collectionModel.add(linkTo(methodOn(PersonControllerV2.class).list()).withSelfRel());

        return collectionModel;
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML }, consumes = {
                    MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    public PersonVOV2 create(@RequestBody PersonVOV2 person) {
        var vo = personService.createV2(person);

        vo.add(linkTo(methodOn(PersonControllerV2.class).create(vo)).slash(vo.getId()).withSelfRel());

        vo.add(linkTo(methodOn(PersonControllerV2.class)
                .list()).withRel(IanaLinkRelations.COLLECTION));

        return vo;
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML }, consumes = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML })
    public PersonVOV2 update(@PathVariable(value = "id") Long id, @RequestBody PersonVOV2 person)
            throws ResourceNotFoundException {
        var vo = personService.updateV2(id, person);

        vo.add(linkTo(methodOn(PersonControllerV2.class).update(id, vo)).withSelfRel());

        vo.add(linkTo(methodOn(PersonControllerV2.class)
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
    public PersonVOV2 get(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        var vo = personService.getV2(id);

        vo.add(linkTo(methodOn(PersonControllerV2.class).get(id)).withSelfRel());

        vo.add(linkTo(methodOn(PersonControllerV2.class)
                .list()).withRel(IanaLinkRelations.COLLECTION));

        return vo;
    }

}
