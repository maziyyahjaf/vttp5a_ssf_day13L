package sg.edu.nus.iss.vttp5a_ssf_day13L.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp5a_ssf_day13L.model.Person;
import sg.edu.nus.iss.vttp5a_ssf_day13L.repo.PersonRepo;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Person findById(String personId) {
        return personRepo.findById(personId);
    }

    public Boolean create(Person person) {
        return personRepo.create(person);
    }

    public Boolean delete(Person person) {
        return personRepo.delete(person);
    }

    public Boolean update(Person person) {
        return personRepo.update(person);
    }

    
    
}
