package spring.data.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import spring.data.demo.modal.Employee;
import spring.data.demo.modal.Gender;
import spring.data.demo.repositories.EmployeeRepository;

import javax.transaction.Transactional;
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
    void findDistinctUsername(){

        List<Employee> distinctEmail = employeeRepository.findDistinctEmployeeByEmail("email1@example.com");

        long totalEmployeesCount = employeeRepository.count();

        assertAll("Only one email",
                () -> assertThat(distinctEmail).isNotNull(),
                () -> assertThat(distinctEmail).hasSize(1));
    }

    @Test
    @Transactional
    void deleteUsername1(){

        long deleteUsername1 = employeeRepository.removeByUsername("username1");

        long totalEmployeesCount = employeeRepository.count();

        assertAll("Delete User1",
                () -> assertThat(deleteUsername1).isEqualTo(1),
                () -> assertThat(totalEmployeesCount).isEqualTo(3));
    }

    @Test
    void thereAreOneActive(){

        long activeEmployeesCount = employeeRepository.countByActiveTrue();

        assertAll("There is only one active employees",
                () -> assertThat(activeEmployeesCount).isEqualTo(1));
    }

    @Test
    void outOfFourEmployeesJustTakeTwo(){

        Pageable pageable = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "username"));
        Page<Employee> twoEmployees = employeeRepository.findAll(pageable);

        assertAll("only two employees",
                () -> assertThat(twoEmployees).isNotNull(),
                () -> assertThat(twoEmployees).hasSize(2),
                () -> assertThat(twoEmployees.get().findFirst().get().getUsername()).isEqualTo("username2"));
    }

    @Test
    void thereAre4EmployeesSorted(){

        List<Employee> employeeAge20 = employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "username"));

        assertAll("There are four employees",
                () -> assertThat(employeeAge20).isNotNull(),
                () -> assertThat(employeeAge20).hasSize(4),
                () -> assertThat(employeeAge20.get(0).getUsername()).isEqualTo("username4"));
    }

    @Test
    void testAge20_thereAreTwoEmployees(){

        List<Employee> employeeAge20 = employeeRepository.findByAge(20, PageRequest.of(1, 2));

        assertAll("Two employees with age of 20",
                () -> assertThat(employeeAge20).isNotNull(),
                () -> assertThat(employeeAge20).hasSize(2));
    }

    @Test
    void findActiveUserAndPaginate(){

        List<Employee> inactiveUsers = employeeRepository.findByActiveFalse(PageRequest.of(1, 2));

        assertAll("Two inactive employees are found",
                () -> assertThat(inactiveUsers).isNotNull(),
                () -> assertThat(inactiveUsers).hasSize(2));
    }


    @Test
    void test_jpa_boolean_true_false(){

        List<Employee> activeEmployees = employeeRepository.findByActiveTrue();

        assertAll("Total number of active employees is 1",
                () -> assertThat(activeEmployees).isNotNull(),
                () -> assertThat(activeEmployees).hasSize(1),
                () -> assertThat(activeEmployees.get(0).getEmail()).isEqualTo("email1@example.com"));

        List<Employee> inActiveUsers = employeeRepository.findByActiveFalse();

        assertAll("There are three inactive employees",
                () -> assertThat(inActiveUsers).isNotNull(),
                () -> assertThat(inActiveUsers).hasSize(3),
                () -> assertThat(inActiveUsers.get(0).getEmail()).isEqualTo("email2@example.com"));


    }

    @Test
    void findEmployeeByUsername_foundEmployeeWithUsernameOfUsername1(){

        Optional<Employee> username1 = employeeRepository.findByUsername("username1");

        assertAll("Username1 is found",
                () -> assertThat(username1).isNotEmpty(),
                () -> assertThat(username1.get()).isNotNull(),
                () -> assertThat(username1.get().getUsername()).isEqualTo("username1"));

    }

    @Test
    void findInactiveEmployee_foundOneInActiveEmployee(){

        List<Employee> inactiveEmployee = employeeRepository.findByActive(false);

        assertAll("Total number of inactive employee is 1",
                () -> assertThat(inactiveEmployee).isNotNull(),
                () -> assertThat(inactiveEmployee).hasSize(3),
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

        Employee employee3 = new Employee();
        employee3.setActive(false);
        employee3.setAge(22);
        employee3.setEmail("email3@example.com");
        employee3.setUsername("username3");
        employee3.setGender(Gender.MALE);

        Employee employee4 = new Employee();
        employee4.setActive(false);
        employee4.setAge(20);
        employee4.setEmail("email4@example.com");
        employee4.setUsername("username4");
        employee4.setGender(Gender.FEMALE);

        return Arrays.asList(employee1, employee2, employee3, employee4);
    }

    @AfterEach
    public void afterAllTests(){
        employeeRepository.deleteAll();
    }

}
