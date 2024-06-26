package jaye.spring.mockito.springboottestingjunitandmockito.service;

import jaye.spring.mockito.springboottestingjunitandmockito.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity createEmployee(EmployeeDTO employeeDTO);

    ResponseEntity findEmployee(String empId);
}
