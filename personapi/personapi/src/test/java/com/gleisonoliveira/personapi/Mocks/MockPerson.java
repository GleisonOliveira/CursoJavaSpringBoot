package com.gleisonoliveira.personapi.Mocks;

import java.util.ArrayList;
import java.util.List;

import com.gleisonoliveira.personapi.Data.VO.V1.PersonVO;
import com.gleisonoliveira.personapi.Models.Person;

public class MockPerson {
    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonVO mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 1; i <= 10; i++) {
            persons.add(mockEntity(i));
        }

        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            persons.add(mockVO(i));
        }

        return persons;
    }

    private Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Addres Test" + number)
                .setFirstName("First Name Test" + number)
                .setGender(((number % 2) == 0) ? "M" : "F")
                .setId(number.longValue())
                .setLastName("Last Name Test" + number);

        return person;
    }

    private PersonVO mockVO(Integer number) {
        PersonVO person = new PersonVO();
        person.setAddress("Addres Test" + number)
                .setFirstName("First Name Test" + number)
                .setGender(((number % 2) == 0) ? "M" : "F")
                .setId(number.longValue())
                .setLastName("Last Name Test" + number);

        return person;
    }
}
