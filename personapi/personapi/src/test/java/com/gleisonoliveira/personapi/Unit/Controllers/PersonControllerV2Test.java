package com.gleisonoliveira.personapi.Unit.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.gleisonoliveira.personapi.Controllers.PersonControllerV2;
import com.gleisonoliveira.personapi.Data.VO.V2.PersonVOV2;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Mocks.MockPerson;
import com.gleisonoliveira.personapi.Mocks.MockPersonV2;
import com.gleisonoliveira.personapi.Models.Person;
import com.gleisonoliveira.personapi.Repository.Person.PersonRepository;
import com.gleisonoliveira.personapi.Services.Person.PersonService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PersonControllerV2Test {
    @InjectMocks
    private PersonControllerV2 personController;

    @MockBean
    PersonRepository personRepository;

    @SpyBean
    private PersonService personService;

    MockPersonV2 input;

    private Person person;

    @BeforeEach
    void setUp() {
        input = new MockPersonV2();
        MockitoAnnotations.openMocks(this);

        person = input.mockEntity();
        person.setId(1L);
    }

    @Test
    void testCreate() {
        when(personRepository.save(person)).thenReturn(person);

        PersonVOV2 personVO = input.mockVO();
        personVO.setId(person.getId());

        var result = personController.create(personVO);

        assertEntityResult(result, person);
        assertEntityLinkWithCollection(result);
    }

    @Test
    void testDelete() throws ResourceNotFoundException {
        when(personRepository.getByID(1L)).thenReturn(person);

        doNothing().when(personRepository).delete(person);

        personController.delete(1L);

        verify(personRepository, times(1)).delete(person);
    }

    @Test
    void testGet() throws ResourceNotFoundException {
        when(personRepository.getByID(1L)).thenReturn(person);

        var result = personController.get(1L);

        assertEntityResult(result, person);
        assertEntityLinkWithCollection(result);
    }

    @Test
    void testList() {
        var persons = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(persons);

        var result = personController.list();

        assertTrue(result.getContent().size() > 0);

        PersonVOV2 obj1 = (PersonVOV2) result.getContent().toArray()[0];
        obj1.setId(1L);

        var person1 = persons.get(0);
        person1.setId(1L);

        assertEntityResult(obj1, person1);
        
        assertEntityLink(obj1);
    }

    @Test
    void testUpdate() throws ResourceNotFoundException {
        when(personRepository.getByID(1L)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);

        PersonVOV2 personVO = input.mockVO();
        personVO.setId(person.getId());

        var result = personController.update(1L, personVO);

        assertEntityResult(result, person);
        assertEntityLinkWithCollection(result);
    }

    /**
     * Assert entity fields
     * 
     * @param result
     * @param person
     */
    private void assertEntityResult(PersonVOV2 result, Person person) {
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());
    }

    private void assertEntityLinkWithCollection(PersonVOV2 result) {
        assertTrue(result.toString()
                .contains("[<http://localhost/person/v2/1>;rel=\"self\", <http://localhost/person/v2>;rel=\"collection\"]"));
    }

    private void assertEntityLink(PersonVOV2 result) {
        assertTrue(result.toString()
                .contains("<http://localhost/person/v2/1>"));
    }
}
