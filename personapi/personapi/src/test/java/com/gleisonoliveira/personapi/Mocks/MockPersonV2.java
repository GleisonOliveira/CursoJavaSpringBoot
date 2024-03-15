package com.gleisonoliveira.personapi.Mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gleisonoliveira.personapi.Data.VO.V2.PersonVOV2;
import com.gleisonoliveira.personapi.Models.Person;

public class MockPersonV2 {
    private static Date birthDay = new Date();

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonVOV2 mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            persons.add(mockEntity(i));
        }

        return persons;
    }

    public List<PersonVOV2> mockVOList() {
        List<PersonVOV2> persons = new ArrayList<>();
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
                .setBirthDay(birthDay)
                .setLastName("Last Name Test" + number);

        return person;
    }

    private PersonVOV2 mockVO(Integer number) {
        PersonVOV2 person = new PersonVOV2();
        person.setAddress("Addres Test" + number)
                .setFirstName("First Name Test" + number)
                .setGender(((number % 2) == 0) ? "M" : "F")
                .setId(number.longValue())
                .setBirthDay(birthDay)
                .setLastName("Last Name Test" + number);

        return person;
    }
}
