package com.startignspring.firstproject.unittests.mockito.services;

import com.startignspring.firstproject.controllers.PersonController;
import com.startignspring.firstproject.data.vo.v1.PersonVO;
import com.startignspring.firstproject.exceptions.RequiredObjectIsNullException;
import com.startignspring.firstproject.exceptions.ResourceNotFoundException;
import com.startignspring.firstproject.mapper.ModelMapper;
import com.startignspring.firstproject.models.Person;
import com.startignspring.firstproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public PersonVO findById(Long id){
        logger.info("find an person for findById");

        var entity = personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Person not found for this id :: " + id));
        PersonVO vo = ModelMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll(){
        logger.info("find all persons for findAll");
        var persons = ModelMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }


    public PersonVO create(PersonVO person){
        if (person == null) throw new RequiredObjectIsNullException();

        var entity = ModelMapper.parseObject(person, Person.class);
        var vo = ModelMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person, Long id){
        if (person == null) throw new RequiredObjectIsNullException();

        var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found" +
                " for this id :: " + person.getKey()));
        entity.setId(id);
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = ModelMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public void delete(Long id){

        logger.info("delete an person for delete");

        var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found" +
                " for this id :: " + id));
        personRepository.delete(entity);
    }
}
