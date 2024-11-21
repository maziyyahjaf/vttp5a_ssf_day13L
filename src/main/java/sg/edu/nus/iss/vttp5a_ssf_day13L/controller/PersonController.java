package sg.edu.nus.iss.vttp5a_ssf_day13L.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_ssf_day13L.model.Person;
import sg.edu.nus.iss.vttp5a_ssf_day13L.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String personListing(Model model) {

        List<Person> persons = personService.findAll();
        model.addAttribute("persons", persons);

        return "personlist";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        
        Person p = new Person();
        model.addAttribute("person", p);
        return "personcreate";
        
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "personcreate";
        }

        Person p = new Person(person.getFirstName(), person.getLastName(), person.getEmail(), person.getSalary(), person.getDob(), person.getTelephone(), person.getPostalCode());

        personService.create(p);

        return "redirect:/persons";
    }

    @GetMapping("/delete/{person-id}")
    public String deletePerson(@PathVariable("person-id") String personId) {

        Person p = personService.findById(personId);
        personService.delete(p);

        return "redirect:/persons";
    }

    @GetMapping("/update/{person-id}")
    public String editPerson(@PathVariable("person-id") String personId, Model model) {
        Person p = personService.findById(personId);
        model.addAttribute("person", p);

        return "personedit";
    }

    @PostMapping("/update")
    public String postUpdateForm(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {
         
        if (result.hasErrors()) {
            return "personedit";
        }

        personService.update(person);

        return("redirect:/persons");
    }

    

    
    
}
