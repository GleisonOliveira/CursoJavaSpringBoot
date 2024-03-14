package com.gleisonoliveira.personapi.Services.Person;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Models.Person;
import com.gleisonoliveira.personapi.Repository.Person.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    /**
     * Get the person by id
     * 
     * @param id
     * @return
     */
    public Person get(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Person.class.getSimpleName()));
    }

    /**
     * List all persons
     * 
     * @return
     */
    public List<Person> list() {
        return repository.findAll();
    }

    /**
     * Create a new person
     * 
     * @param person
     * @return
     */
    public Person create(Person person) {
        return repository.save(person);
    }

    /**
     * Update a new person
     * 
     * @param id
     * @param person
     * @return
     * @throws ResourceNotFoundException
     */
    public Person update(Long id, Person person) throws ResourceNotFoundException {
        Person savedPerson = get(id);

        savedPerson.setFirstName(person.getFirstName())
                .setLastName(person.getLastName())
                .setAddress(person.getAddress())
                .setGender(person.getGender());

        return repository.save(savedPerson);
    }

    /**
     * Delete a person
     * 
     * @param id
     * @throws ResourceNotFoundException 
     */
    public void delete(Long id) throws ResourceNotFoundException {
        Person savedPerson = get(id);

        repository.delete(savedPerson);
    }
}
