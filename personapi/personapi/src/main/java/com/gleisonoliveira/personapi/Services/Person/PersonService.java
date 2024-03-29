package com.gleisonoliveira.personapi.Services.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gleisonoliveira.personapi.Data.VO.V1.PersonVO;
import com.gleisonoliveira.personapi.Data.VO.V2.PersonVOV2;
import com.gleisonoliveira.personapi.Exceptions.RequiredObjectIsNullException;
import com.gleisonoliveira.personapi.Exceptions.ResourceNotFoundException;
import com.gleisonoliveira.personapi.Mapper.ModelMapperMapper;
import com.gleisonoliveira.personapi.Models.Person;
import com.gleisonoliveira.personapi.Repository.BaseRepository;
import com.gleisonoliveira.personapi.Repository.Person.PersonRepository;
import com.gleisonoliveira.personapi.Services.BaseService;

@Service
public class PersonService implements BaseService<Person, Long> {
    @Autowired
    private PersonRepository repository;

    @Override
    public BaseRepository<Person, Long> getRepository() {
        return repository;
    }

    @Override
    public String getEntityName() {
        return Person.class.getSimpleName();
    }

    /**
     * Get the person by id
     * 
     * @param id
     * @return
     */
    public PersonVO get(Long id) throws ResourceNotFoundException {
        Person person = getByID(id);

        return ModelMapperMapper.parseObject(person, PersonVO.class);
    }

    /**
     * List all persons
     * 
     * @return
     */
    public List<PersonVO> list() {
        List<Person> persons = repository.findAll();

        return ModelMapperMapper.parseListObjects(persons, PersonVO.class);
    }

    /**
     * Create a new person
     * 
     * @param personVO
     * @return
     * @throws RequiredObjectIsNullException
     */
    public PersonVO create(PersonVO personVO) throws RequiredObjectIsNullException {
        if (personVO == null)
            throw new RequiredObjectIsNullException(Person.class.getSimpleName());

        Person person = repository.save(ModelMapperMapper.parseObject(personVO, Person.class));

        return ModelMapperMapper.parseObject(person, PersonVO.class);
    }

    /**
     * Update a new person
     * 
     * @param id
     * @param personVO
     * @return
     * @throws ResourceNotFoundException
     * @throws RequiredObjectIsNullException
     */
    public PersonVO update(Long id, PersonVO personVO) throws ResourceNotFoundException, RequiredObjectIsNullException {
        if (personVO == null)
            throw new RequiredObjectIsNullException(Person.class.getSimpleName());

        Person savedPerson = getByID(id);

        savedPerson.setFirstName(personVO.getFirstName())
                .setLastName(personVO.getLastName())
                .setAddress(personVO.getAddress())
                .setGender(personVO.getGender());

        return ModelMapperMapper.parseObject(repository.save(savedPerson), PersonVO.class);
    }

    /**
     * Delete a person
     * 
     * @param id
     * @throws ResourceNotFoundException
     */
    public void delete(Long id) throws ResourceNotFoundException {
        Person savedPerson = getByID(id);

        repository.delete(savedPerson);
    }

    /**
     * Get the person by id
     * 
     * @param id
     * @return
     */
    public PersonVOV2 getV2(Long id) throws ResourceNotFoundException {
        Person person = getByID(id);

        return ModelMapperMapper.parseObject(person, PersonVOV2.class);
    }

    /**
     * List all persons
     * 
     * @return
     */
    public List<PersonVOV2> listV2() {
        List<Person> persons = repository.findAll();

        return ModelMapperMapper.parseListObjects(persons, PersonVOV2.class);
    }

    /**
     * Create a new person
     * 
     * @param personVO
     * @return
     * @throws RequiredObjectIsNullException
     */
    public PersonVOV2 createV2(PersonVOV2 personVO) throws RequiredObjectIsNullException {
        if (personVO == null)
            throw new RequiredObjectIsNullException(Person.class.getSimpleName());

        Person person = repository.save(ModelMapperMapper.parseObject(personVO, Person.class));

        return ModelMapperMapper.parseObject(person, PersonVOV2.class);
    }

    /**
     * Update a new person
     * 
     * @param id
     * @param personVO
     * @return
     * @throws ResourceNotFoundException
     * @throws RequiredObjectIsNullException
     */
    public PersonVOV2 updateV2(Long id, PersonVOV2 personVO)
            throws ResourceNotFoundException, RequiredObjectIsNullException {
        if (personVO == null)
            throw new RequiredObjectIsNullException(Person.class.getSimpleName());

        Person savedPerson = getByID(id);

        savedPerson.setFirstName(personVO.getFirstName())
                .setLastName(personVO.getLastName())
                .setAddress(personVO.getAddress())
                .setGender(personVO.getGender())
                .setBirthDay(personVO.getBirthDay());

        return ModelMapperMapper.parseObject(repository.save(savedPerson), PersonVOV2.class);
    }
}
