package com.startignspring.firstproject.unittests.mockito.services;

import com.startignspring.firstproject.data.vo.v1.PersonVO;
import com.startignspring.firstproject.exceptions.RequiredObjectIsNullException;
import com.startignspring.firstproject.models.Person;
import com.startignspring.firstproject.repositories.PersonRepository;
import com.startignspring.firstproject.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest extends Object {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;


    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person entity = input.mockEntity();
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
//        System.out.println(result.toString());
        assertTrue(result.toString().contains(" [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test0", result.getFirstName());
        assertEquals("Last Name Test0", result.getLastName());
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("Male", result.getGender());

    }

    @Test
    void findAll() {
        List<Person> listEntities = input.mockEntityList();

        when(repository.findAll()).thenReturn(listEntities);


        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var result = people.get(1);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
//        System.out.println(result.toString());
        assertTrue(result.toString().contains(" [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());

        var result13 = people.get(13);
        assertNotNull(result13);
        assertNotNull(result13.getKey());
        assertNotNull(result13.getLinks());
//        System.out.println(result.toString());
        assertTrue(result13.toString().contains(" [</api/person/v1/13>;rel=\"self\"]"));
        assertEquals("First Name Test13", result13.getFirstName());
        assertEquals("Last Name Test13", result13.getLastName());
        assertEquals("Addres Test13", result13.getAddress());
        assertEquals("Female", result13.getGender());


    }

    @Test
    void create() {
        //Quando recebo sem um id
        Person entity = input.mockEntity();
        //Devo retornar com um id
        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO();
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
//        System.out.println(result.toString());
        assertTrue(result.toString().contains(" [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test0", result.getFirstName());
        assertEquals("Last Name Test0", result.getLastName());
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("Male", result.getGender());

    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "Required Object is null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }
    @Test
    void update() {
        Person entity = input.mockEntity();
        entity.setId(1L);

        Person persisted = entity;

        PersonVO vo = input.mockVO();
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);


        var result = service.update(vo,1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
//        System.out.println(result.toString());
        assertTrue(result.toString().contains(" [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test0", result.getFirstName());
        assertEquals("Last Name Test0", result.getLastName());
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("Male", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null, null);
        });
        String expectedMessage = "Required Object is null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    void delete() {
        Person entity = input.mockEntity();
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(entity));

        service.delete(1L);
    }
}