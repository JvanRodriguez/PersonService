package co.edu.uptc.dto;

import lombok.Data;

@Data
public class PersonRequest {
    private String name;
    private String lastName;
    private int age;
    private String documentNumber;
    private String phoneNumber;
}