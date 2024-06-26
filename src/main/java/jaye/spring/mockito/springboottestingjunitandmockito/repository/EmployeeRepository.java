package jaye.spring.mockito.springboottestingjunitandmockito.repository;

import jaye.spring.mockito.springboottestingjunitandmockito.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByEmpId(String empId);
}
