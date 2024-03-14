package com.gleisonoliveira.personapi.Repository.Person;

import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Models.Person;

public interface PersonRepositoryBase {
    /**
     * Get the person by id
     * 
     * @param id
     * @return
     * @throws ResourceNotFoundException 
     */
    public Person getByID(Long id) throws ResourceNotFoundException;
}
