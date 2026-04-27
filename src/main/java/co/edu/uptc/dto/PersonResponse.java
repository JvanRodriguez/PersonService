package co.edu.uptc.dto;

import co.edu.uptc.Model.Person;
import lombok.Data;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
public class PersonResponse {
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private String documentNumber;
    private String phoneNumber;
    private String message;
    private String ipAddress;
    
    public PersonResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.lastName = person.getLastName();
        this.age = person.getAge();
        this.documentNumber = person.getDocumentNumber();
        this.phoneNumber = person.getPhoneNumber();
        this.message = "OK";
        this.ipAddress = getContainerIp();
    }
    
    public PersonResponse(PersonDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.age = dto.getAge();
        this.documentNumber = dto.getDocumentNumber();
        this.phoneNumber = dto.getPhoneNumber();
        this.message = "OK";
        this.ipAddress = getContainerIp();
    }
    
    public PersonResponse(String message) {
        this.message = message;
        this.ipAddress = getContainerIp();
    }
    
    private String getContainerIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}