package com.gleisonoliveira.personapi.Services;

import java.io.Serializable;

import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Repository.BaseRepository;

public interface BaseService<T extends Serializable, ID extends Serializable> {
    public static String entityName = "";

    /**
     * Set the entity name
     * 
     * @param entityName
     */
    String getEntityName();

    /**
     * Get the current repository
     * 
     * @return
     */
    BaseRepository<T, ID> getRepository();

    /**
     * Get the entity by id
     * 
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    default T getByID(ID id) throws ResourceNotFoundException {
        var entity = getRepository().findById(id);

        if (!entity.isPresent()) {
            throw new ResourceNotFoundException(getEntityName());
        }

        return (T) entity.get();
    }
}
