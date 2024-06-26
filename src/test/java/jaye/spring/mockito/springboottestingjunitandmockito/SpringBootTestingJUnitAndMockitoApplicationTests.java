package jaye.spring.mockito.springboottestingjunitandmockito;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import jaye.spring.mockito.springboottestingjunitandmockito.controller.EmployeeController;
import jaye.spring.mockito.springboottestingjunitandmockito.dto.EmployeeDTO;
import jaye.spring.mockito.springboottestingjunitandmockito.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootTestingJUnitAndMockitoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setEmpId("123");
        employeeDTO.setEmail("email");
        employeeDTO.setAddress("address");
        employeeDTO.setPhone("phone");
        employeeDTO.setDepartment("department");

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(employeeService.createEmployee(employeeDTO)).thenReturn(responseEntity);

        mockMvc.perform(post("/employee/v1/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindEmployee() throws Exception {
        String empId = "123";
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(employeeService.findEmployee(empId)).thenReturn(responseEntity);

        mockMvc.perform(get("/employee/v1/find/{empId}", empId)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}