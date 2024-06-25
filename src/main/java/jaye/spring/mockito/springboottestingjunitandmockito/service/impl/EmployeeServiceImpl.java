package jaye.spring.mockito.springboottestingjunitandmockito.service.impl;

import jaye.spring.mockito.springboottestingjunitandmockito.dto.EmployeeDTO;
import jaye.spring.mockito.springboottestingjunitandmockito.repository.EmployeeRepository;
import jaye.spring.mockito.springboottestingjunitandmockito.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity createEmployee(EmployeeDTO employeeDTO) {
        return null;
    }
}
