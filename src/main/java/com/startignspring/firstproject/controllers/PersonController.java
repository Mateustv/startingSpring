package com.startignspring.firstproject.controllers;
import com.startignspring.firstproject.models.Person;
import com.startignspring.firstproject.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Person findById(@PathVariable(value = "id") String id){
        return personService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Person> findAll(){
        return personService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createPerson(@RequestBody Person person){
        return personService.create(person);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Person updatePerson(@RequestBody Person person){
        return personService.update(person);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable(value = "id") String id){
        personService.delete(id);
    }
}
