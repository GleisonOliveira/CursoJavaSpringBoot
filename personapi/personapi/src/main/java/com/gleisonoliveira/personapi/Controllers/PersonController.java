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
import com.gleisonoliveira.personapi.Exceptions.ExceptionResponse;
import com.gleisonoliveira.personapi.Exceptions.RequiredObjectIsNullException;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Services.Person.PersonService;
import com.gleisonoliveira.personapi.Util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Endpoint to manage people")

public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "List all people", description = "List all people", tags = { "People" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))
            })
    })
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
                    MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Create a new person", description = "Create a new person", tags = { "People" }, responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))
            })
    })
    public PersonVO create(@RequestBody PersonVO person) throws RequiredObjectIsNullException {
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
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }, consumes = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })
    @Operation(summary = "Update a person", description = "Update a person", tags = { "People" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))
            }),
            @ApiResponse(description = "Not found", responseCode = "404", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))
            }),
    })
    public PersonVO update(@PathVariable(value = "id") Long id, @RequestBody PersonVO person)
            throws ResourceNotFoundException, RequiredObjectIsNullException {
        var vo = personService.update(id, person);

        vo.add(linkTo(methodOn(PersonController.class)
                .update(id, vo))
                .withSelfRel());

        vo.add(linkTo(methodOn(PersonController.class)
                .list()).withRel(IanaLinkRelations.COLLECTION));

        return vo;
    }

    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a person by id", description = "Delete a person by id", tags = {
            "People" }, responses = {
                    @ApiResponse(description = "No content", responseCode = "204"),
                    @ApiResponse(description = "Not found", responseCode = "404", content = {
                            @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))
                    }),
            })
    public void delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        personService.delete(id);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    @Operation(summary = "Get a person by id", description = "Get a person by id", tags = { "People" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))
            }),
            @ApiResponse(description = "Not found", responseCode = "404", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))
            }),
    })
    public PersonVO get(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        var vo = personService.get(id);

        vo.add(linkTo(methodOn(PersonController.class).get(id)).withSelfRel());

        vo.add(linkTo(methodOn(PersonController.class)
                .list()).withRel(IanaLinkRelations.COLLECTION));

        return vo;
    }

}
