package co.edu.uptc.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.Service.PersonService;
import co.edu.uptc.dto.PersonRequest;
import co.edu.uptc.dto.PersonResponse; 

@RestController
@RequestMapping("/persons")
public class PersonController {
    
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String sayHi() {
        return "Hi, welcome to the Person API!";
    }

    @GetMapping("/{id}")
    public PersonResponse getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/all")
    public List<PersonResponse> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping("/add")
    public PersonResponse addPerson(@RequestBody PersonRequest person) {
        return personService.savePerson(person);
    }
}
