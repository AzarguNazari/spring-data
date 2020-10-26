package spring.data.demo.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.data.demo.modal.Employee;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findEmployeeByUsername(String username);
    List<Employee> findEmployeeByIsActiveTrue();
    List<Employee> findEmployeeByIsActive(boolean isActive);
    Optional<Employee> findEmployeeByEmail(String email);
    List<Employee> findEmployeeByIsActive(Pageable pageable);
    List<Employee> findEmployeeByIsActive(Sort sort);
    Optional<Employee> findByUsernameAndEmail(String username, String email);
    Optional<Employee> findByUsernameOrEmail(String username, String email);
    List<Employee> findAgeBetween(int start, int end);
    List<Employee> findByBirthDateBetween(Date start, Date endDate);
    List<Employee> findByAgeLessThan(int young);
    List<Employee> findByAgeGreaterThan(int retiredAge);
    List<Employee> findByBirthDateAfter(Date after);
    List<Employee> findByBirthDateBefore(Date before);
    List<Employee> findByUsernameLike(String username);
    List<Employee> findByFirstNameNotLike(String firstName);
    List<Employee> findByLastNameEnding(String endingLastName);
    List<Employee> findByFirstnameContaining(String firstName);
    List<Employee> findByAgeOrderByFirstnameDesc(int age);
    List<Employee> findAgeIn(List<Integer> ages);
    List<Employee> findAgeNotIn(List<Integer> ages);
    List<Employee> findByFirstnameIgnoreCase(String firstname);

}
