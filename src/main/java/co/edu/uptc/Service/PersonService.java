package co.edu.uptc.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import co.edu.uptc.dto.PersonDto;
import co.edu.uptc.dto.PersonRequest;
import co.edu.uptc.dto.PersonResponse;
import co.edu.uptc.entity.PersonEntity;
import co.edu.uptc.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonResponse savePerson(PersonRequest request) {
        if (personExists(request.getName(), request.getLastName())) {
            return new PersonResponse("La persona ya existe.");
        }
        
        PersonEntity entity = new PersonEntity();
        entity.setName(request.getName());
        entity.setLastName(request.getLastName());
        entity.setAge(request.getAge());
        entity.setDocumentNumber(request.getDocumentNumber());
        entity.setPhoneNumber(request.getPhoneNumber());
        
        repository.save(entity);
        return new PersonResponse("Persona guardada con ID: " + entity.getId());
    }

    private boolean personExists(String name, String lastName) {
        return repository.findAll().stream()
            .anyMatch(p -> p.getName().equals(name) && p.getLastName().equals(lastName));
    }

    public PersonResponse getPersonById(Long id) {
        return repository.findById(id)
            .map(entity -> new PersonResponse(new PersonDto(entity)))
            .orElse(new PersonResponse("Persona no encontrada"));
    }

    public List<PersonResponse> getAllPersons() {
        return repository.findAll().stream()
            .map(entity -> new PersonResponse(new PersonDto(entity)))
            .collect(Collectors.toList());
    }
}