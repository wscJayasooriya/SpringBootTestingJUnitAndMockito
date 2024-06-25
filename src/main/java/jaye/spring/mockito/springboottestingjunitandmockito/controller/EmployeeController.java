package jaye.spring.mockito.springboottestingjunitandmockito.controller;

import jaye.spring.mockito.springboottestingjunitandmockito.dto.EmployeeDTO;
import jaye.spring.mockito.springboottestingjunitandmockito.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/employee/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }
}
