package com.startignspring.firstproject.services;

import com.startignspring.firstproject.data.vo.v1.PersonVO;
import com.startignspring.firstproject.exceptions.ResourceNotFoundException;
import com.startignspring.firstproject.mapper.DozerMapper;
import com.startignspring.firstproject.models.Person;
import com.startignspring.firstproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonService {
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public PersonVO findById(Long id){
        logger.info("find an person for findById");

        var entity = personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Person not found for this id :: " + id));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        logger.info("find all persons for findAll");
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }


    public PersonVO create(PersonVO person){
        logger.info("create an person for create");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO person, Long id){
        var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found" +
                " for this id :: " + person.getId()));
        entity.setId(id);
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }

    public void delete(Long id){

        logger.info("delete an person for delete");

        var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found" +
                " for this id :: " + id));
        personRepository.delete(entity);
    }
}
