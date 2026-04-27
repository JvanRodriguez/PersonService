package co.edu.uptc.dto;

import co.edu.uptc.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private String documentNumber;
    private String phoneNumber;
    
    public PersonDto(PersonEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.lastName = entity.getLastName();
        this.age = entity.getAge();
        this.documentNumber = entity.getDocumentNumber();
        this.phoneNumber = entity.getPhoneNumber();
    }
}