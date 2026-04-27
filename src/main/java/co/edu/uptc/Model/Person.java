package co.edu.uptc.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private String documentNumber;
    private String phoneNumber;
}