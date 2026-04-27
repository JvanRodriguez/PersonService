package co.edu.uptc.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.uptc.Service.PersonService;
import co.edu.uptc.dto.PersonRequest;
import co.edu.uptc.dto.PersonResponse; 

@RestController
@RequestMapping("/persons")
public class PersonController {
    
    private final PersonService personService;
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

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
    public ResponseEntity<?> getAllPersons() {
        long count = personService.getCount();
        
        // Protección: si hay más de 5000 registros, rechaza
        if (count > 5000) {
            log.warn("Bloqueado /all con {} registros. Usa paginación.", count);
            return ResponseEntity.badRequest()
                .body("Error: " + count + " registros. Usa /persons/page?page=0&size=20");
        }
        
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @PostMapping("/add")
    public PersonResponse addPerson(@RequestBody PersonRequest person) {
        return personService.savePerson(person);
    }

    @GetMapping("/page")
    public Page<PersonResponse> getPersonsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.info("Solicitando página {} con tamaño {}", page, size);
        
        if (size > 1000) {
            log.warn("Tamaño de página solicitado excede límite: {}", size);
            throw new IllegalArgumentException("El tamaño máximo por página es 1000");
        }
        
        Pageable pageable = PageRequest.of(page, size);
        return personService.getPersonsPaginated(pageable);
    }
}
