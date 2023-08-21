package com.employee.registration;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.employee.registration.controller.EmployeeController;
import com.employee.registration.domain.Employee;
import com.employee.registration.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeService.fetchAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/registrations/getAllEmployees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        int employeeId = 1;
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        when(employeeService.fetchEmployeeById(employeeId)).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/registrations/getEmployeeById/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(employeeId));
    }
    
    @Test
    public void testGetEmployeeById_InvalidId() throws Exception {
        int invalidId = -1;

        when(employeeService.fetchEmployeeById(invalidId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/registrations/getEmployeeById/{id}", invalidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSaveEmployee_InvalidInput() throws Exception {
        // Create an employee object with invalid input (e.g., missing required fields)
        Employee invalidEmployee = new Employee();

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/registrations/saveEmployee")
                .content(asJsonString(invalidEmployee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Helper method to convert an object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

    

