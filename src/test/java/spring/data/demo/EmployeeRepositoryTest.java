package spring.data.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.data.demo.modal.Employee;
import spring.data.demo.modal.Gender;
import spring.data.demo.repositories.EmployeeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void forEachTest(){
        addEmployees();
    }

    @Test
    void findActiveUsers_foundOneActiveUsers(){

        List<Employee> activeEmployees = employeeRepository.findEmployeeByIsActiveTrue();

        assertAll("Total number of active employees is 1",
                () -> assertThat(activeEmployees).isNotNull(),
                () -> assertThat(activeEmployees).hasSize(1),
                () -> assertThat(activeEmployees.get(0).getEmail()).isEqualTo("email1@example.com"));

    }

    @Test
    void findEmployeeByEmail_foundEmployeeWithEmailofEmail1(){

        Optional<Employee> username1 = employeeRepository.findEmployeeByUsername("username1");

        assertAll("Username1 is found",
                () -> assertThat(username1).isNotEmpty(),
                () -> assertThat(username1.get()).isNotNull(),
                () -> assertThat(username1.get().getUsername()).isEqualTo("username1"));

    }

    @Test
    void findInactiveEmployee_foundOneInActiveEmployee(){

        List<Employee> inactiveEmployee = employeeRepository.findEmployeeByIsActive(false);

        assertAll("Total number of inactive employee is 1",
                () -> assertThat(inactiveEmployee).isNotNull(),
                () -> assertThat(inactiveEmployee).hasSize(1),
                () -> assertThat(inactiveEmployee.get(0).getEmail()).isEqualTo("email2@example.com"));

    }

    private void addEmployees(){
        List<Employee> employees = generateEmployee();
        employees.forEach(employeeRepository::save);
    }

    private List<Employee> generateEmployee(){

        Employee employee1 = new Employee();
        employee1.setActive(true);
        employee1.setAge(18);
        employee1.setEmail("email1@example.com");
        employee1.setUsername("username1");
        employee1.setGender(Gender.FEMALE);

        Employee employee2 = new Employee();
        employee2.setActive(false);
        employee2.setAge(20);
        employee2.setEmail("email2@example.com");
        employee2.setUsername("username2");
        employee2.setGender(Gender.MALE);


        return Arrays.asList(employee1, employee2);
    }

    @AfterEach
    public void afterAllTests(){
        employeeRepository.deleteAll();
    }

}
