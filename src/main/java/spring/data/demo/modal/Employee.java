package spring.data.demo.modal;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private Gender gender;
    private boolean active;
    private Date birthDate;

}

