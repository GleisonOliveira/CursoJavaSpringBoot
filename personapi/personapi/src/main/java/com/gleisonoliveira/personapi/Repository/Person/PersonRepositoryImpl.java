package com.gleisonoliveira.personapi.Repository.Person;

import org.springframework.data.jpa.repository.JpaContext;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Models.Person;

import jakarta.persistence.EntityManager;

public class PersonRepositoryImpl implements PersonRepositoryBase {
    private final EntityManager em;

    public PersonRepositoryImpl(JpaContext context) {
        this.em = context.getEntityManagerByManagedType(Person.class);
    }

    public Person getByID(Long id) throws ResourceNotFoundException {
        Person person = em.find(Person.class, id);

        if (person == null) {
            throw new ResourceNotFoundException(Person.class.getSimpleName());
        }

        return person;
    }

}
