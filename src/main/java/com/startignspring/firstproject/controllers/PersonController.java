package com.startignspring.firstproject.controllers;
import com.startignspring.firstproject.models.Person;
import com.startignspring.firstproject.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
