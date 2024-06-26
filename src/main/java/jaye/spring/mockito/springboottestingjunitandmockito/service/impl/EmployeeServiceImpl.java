package jaye.spring.mockito.springboottestingjunitandmockito.service.impl;

import jaye.spring.mockito.springboottestingjunitandmockito.dto.EmployeeDTO;
import jaye.spring.mockito.springboottestingjunitandmockito.entity.Employee;
import jaye.spring.mockito.springboottestingjunitandmockito.repository.EmployeeRepository;
import jaye.spring.mockito.springboottestingjunitandmockito.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity createEmployee(EmployeeDTO employeeDTO) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmpId(employeeDTO.getEmpId());
            if (optionalEmployee.isPresent()) {
                return new ResponseEntity<>("Employee already exists", HttpStatus.OK);
            }

            Employee employee = Employee.builder()
                    .empId(employeeDTO.getEmpId())
                    .firstName(employeeDTO.getFirstName())
                    .lastName(employeeDTO.getLastName())
                    .email(employeeDTO.getEmail())
                    .phone(employeeDTO.getPhone())
                    .address(employeeDTO.getAddress())
                    .department(employeeDTO.getDepartment())
                    .build();
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findEmployee(String empId) {

        try{
            EmployeeDTO employeeDTO = new EmployeeDTO();
            Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmpId(empId);
            if(!optionalEmployee.isPresent()){
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
            Employee employee = optionalEmployee.get();

            employeeDTO.setEmpId(employee.getEmpId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhone(employee.getPhone());
            employeeDTO.setAddress(employee.getAddress());
            employeeDTO.setDepartment(employee.getDepartment());

            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
