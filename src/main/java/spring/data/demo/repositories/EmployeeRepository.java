package spring.data.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.data.demo.modal.Employee;

import java.util.List;
import java.util.Optional;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    long deleteByUsername(String username);
    long removeByUsername(String username);
    long countByActiveTrue();
    List<Employee> findAll(Sort sort);
    List<Employee> findDistinctEmployeeByEmail(String email);
    Page<Employee> findAll(Pageable pageable);
    Optional<Employee> findByUsername(String username);
    List<Employee> findByActiveTrue();
    List<Employee> findByActiveFalse();
    List<Employee> findByActive(boolean isActive);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByActiveFalse(Pageable pageable);
    List<Employee> findByAge(int age, Pageable pageable);
//    List<Employee> findByIsActive(Sort sort);
//    Optional<Employee> findByFirstnameAndLastname(String firstName, String lastName);
//    Optional<Employee> findByUsernameOrEmail(String username, String email);
//    List<Employee> findAgeBetween(int start, int end);
//    List<Employee> findByBirthDateBetween(Date start, Date endDate);
//    List<Employee> findByAgeLessThan(int young);
//    List<Employee> findByAgeGreaterThan(int retiredAge);
//    List<Employee> findByBirthDateAfter(Date after);
//    List<Employee> findByBirthDateBefore(Date before);
//    List<Employee> findByUsernameLike(String username);
//    List<Employee> findByFirstNameNotLike(String firstName);
//    List<Employee> findByLastNameEnding(String endingLastName);
//    List<Employee> findByFirstnameContaining(String firstName);
//    List<Employee> findByAgeOrderByFirstnameDesc(int age);
//    List<Employee> findAgeIn(List<Integer> ages);
//    List<Employee> findAgeNotIn(List<Integer> ages);
//    List<Employee> findByFirstnameIgnoreCase(String firstname);

}
