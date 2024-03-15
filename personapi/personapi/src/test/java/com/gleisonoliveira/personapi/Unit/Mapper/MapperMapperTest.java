package com.gleisonoliveira.personapi.Unit.Mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import com.gleisonoliveira.personapi.Data.VO.V1.PersonVO;
import com.gleisonoliveira.personapi.Mapper.ModelMapperMapper;
import com.gleisonoliveira.personapi.Mocks.MockPerson;
import com.gleisonoliveira.personapi.Models.Person;

public class MapperMapperTest {
    static MockPerson mockPerson;

    @BeforeAll
    public static void setUp() {
        mockPerson = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = ModelMapperMapper.parseObject(mockPerson.mockEntity(), PersonVO.class);

        Assertions.assertEquals(Long.valueOf(0L), output.getId());
        Assertions.assertEquals("First Name Test0", output.getFirstName());
        Assertions.assertEquals("Last Name Test0", output.getLastName());
        Assertions.assertEquals("Addres Test0", output.getAddress());
        Assertions.assertEquals("M", output.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = ModelMapperMapper.parseObject(mockPerson.mockVO(), Person.class);

        Assertions.assertEquals(Long.valueOf(0L), output.getId());
        Assertions.assertEquals("First Name Test0", output.getFirstName());
        Assertions.assertEquals("Last Name Test0", output.getLastName());
        Assertions.assertEquals("Addres Test0", output.getAddress());
        Assertions.assertEquals("M", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = ModelMapperMapper.parseListObjects(mockPerson.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);
        
        Assertions.assertEquals(Long.valueOf(1L), outputZero.getId());
        Assertions.assertEquals("First Name Test1", outputZero.getFirstName());
        Assertions.assertEquals("Last Name Test1", outputZero.getLastName());
        Assertions.assertEquals("Addres Test1", outputZero.getAddress());
        Assertions.assertEquals("F", outputZero.getGender());
        
        PersonVO outputSeven = outputList.get(6);
        
        Assertions.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assertions.assertEquals("First Name Test7", outputSeven.getFirstName());
        Assertions.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assertions.assertEquals("Addres Test7", outputSeven.getAddress());
        Assertions.assertEquals("F", outputSeven.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = ModelMapperMapper.parseListObjects(mockPerson.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        Assertions.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assertions.assertEquals("First Name Test0", outputZero.getFirstName());
        Assertions.assertEquals("Last Name Test0", outputZero.getLastName());
        Assertions.assertEquals("Addres Test0", outputZero.getAddress());
        Assertions.assertEquals("M", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        
        Assertions.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assertions.assertEquals("First Name Test7", outputSeven.getFirstName());
        Assertions.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assertions.assertEquals("Addres Test7", outputSeven.getAddress());
        Assertions.assertEquals("F", outputSeven.getGender());
    }
}
