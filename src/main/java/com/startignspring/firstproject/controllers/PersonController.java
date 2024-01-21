package com.startignspring.firstproject.controllers;
import com.startignspring.firstproject.data.vo.v1.PersonVO;
import com.startignspring.firstproject.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;
    @GetMapping(value = "/{id}", produces = "application/json")
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @GetMapping(produces = "application/json")
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces =
            MediaType.APPLICATION_JSON_VALUE)
    public PersonVO createPerson(@RequestBody PersonVO person){
        return personService.create(person);
    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces =
            MediaType.APPLICATION_JSON_VALUE)
    public PersonVO updatePerson(@RequestBody PersonVO person, @PathVariable(value = "id") Long id){
        return personService.update(person, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
