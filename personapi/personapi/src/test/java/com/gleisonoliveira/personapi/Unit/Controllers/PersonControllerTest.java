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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.gleisonoliveira.personapi.Controllers.PersonController;
import com.gleisonoliveira.personapi.Data.VO.V1.PersonVO;
import com.gleisonoliveira.personapi.Exceptions.RequiredObjectIsNullException;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Mocks.MockPerson;
import com.gleisonoliveira.personapi.Models.Person;
import com.gleisonoliveira.personapi.Repository.Person.PersonRepository;
import com.gleisonoliveira.personapi.Services.Person.PersonService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PersonControllerTest {
    @InjectMocks
    private PersonController personController;

    @MockBean
    PersonRepository personRepository;

    @SpyBean
    private PersonService personService;

    MockPerson input;

    private Person person;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

        person = input.mockEntity();
        person.setId(1L);
    }

    @Test
    void testCreate() throws RequiredObjectIsNullException {
        when(personRepository.save(person)).thenReturn(person);

        PersonVO personVO = input.mockVO();
        personVO.setId(person.getId());

        var result = personController.create(personVO);

        assertEntityResult(result, person);
        assertEntityLinkWithCollection(result);
    }

    @Test
    void testDelete() throws ResourceNotFoundException {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        doNothing().when(personRepository).delete(person);

        personController.delete(1L);

        verify(personRepository, times(1)).delete(person);
    }

    @Test
    void testGet() throws ResourceNotFoundException {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var result = personController.get(1L);

        assertEntityResult(result, person);
        assertEntityLinkWithCollection(result);
    }

    @Test
    void testList() {
        var persons = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(persons);

        var result = personController.list();

        assertTrue(result.size() > 0);

        var obj1 = result.get(0);
        obj1.setId(1L);

        var person1 = persons.get(0);
        person1.setId(1L);

        assertEntityResult(obj1, person1);
        assertEntityLink(obj1);
    }

    @Test
    void testUpdate() throws ResourceNotFoundException, RequiredObjectIsNullException {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);

        PersonVO personVO = input.mockVO();
        personVO.setId(person.getId());

        var result = personController.update(1L, personVO);

        assertEntityResult(result, person);
        assertEntityLinkWithCollection(result);
    }

    @Test
    void testCreateWithNull() {
        assertThrows(RequiredObjectIsNullException.class, () -> {
            personController.create(null);
        });
    }

    @Test
    void testUpdateWithNull() {
        assertThrows(RequiredObjectIsNullException.class, () -> {
            personController.update(1L, null);
        });
    }

    /**
     * Assert entity fields
     * 
     * @param result
     * @param person
     */
    private void assertEntityResult(PersonVO result, Person person) {
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());
    }

    private void assertEntityLinkWithCollection(PersonVO result) {
        assertTrue(result.toString()
                .contains("[<http://localhost/person/1>;rel=\"self\", <http://localhost/person>;rel=\"collection\"]"));
    }

    private void assertEntityLink(PersonVO result) {
        assertTrue(result.toString()
                .contains("<http://localhost/person/1>"));
    }
}
