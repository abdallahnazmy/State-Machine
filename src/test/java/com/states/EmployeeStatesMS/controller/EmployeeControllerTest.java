package com.states.EmployeeStatesMS.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.states.EmployeeStatesMS.entity.Contract;
import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.ContractType;
import com.states.EmployeeStatesMS.service.ContractSerciceImpl;
import com.states.EmployeeStatesMS.service.EmployeeServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeServiceImpl employeeService;
    @MockBean
    private ContractSerciceImpl contractSercice;
    private Employee employee;
    private Contract contract;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    @DisplayName("Initializing mock objects before running tests...")
    void init() {
        contract = new Contract();
        contract.setId(1L);
        contract.setContractType(ContractType.FULL_TIME);
        contract.setSalary(2700);

        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Dave");
        employee.setLastName("Austin");
        employee.setAge(26);
        employee.setNumber("01078824135");
        employee.setStartDate(LocalDate.now());
        employee.setContract(contract);
    }

    @Test
    @DisplayName("Add employee API")
    void addEmployeeAPI() throws Exception {
        Mockito.when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);
        String json = mapper.writeValueAsString(employee);
        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.number", Matchers.equalTo("01078824135")));
    }

    @Test
    @DisplayName("Update employee state API")
    void updateStateBeginCheckEvent() throws Exception {

        Mockito.when(employeeService.updateEmployeeState(ArgumentMatchers.any(), anyLong())).thenReturn(employee);
        String json = mapper.writeValueAsString(employee);

        mockMvc.perform(put("/employee").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.number", Matchers.equalTo("01078824135")));

    }

    @Test
    @DisplayName("Get employee API")
    void getEmployee() throws Exception {

        Mockito.when(employeeService.getEmployee(ArgumentMatchers.any())).thenReturn(employee);
        Mockito.when(contractSercice.getContract(anyLong())).thenReturn(Optional.of(contract));

        Employee employee1 = employeeService.getEmployee(1L);
        String json = mapper.writeValueAsString(employee1);
        mockMvc.perform(get("/employee/{id}", 1L).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.number", Matchers.equalTo("01078824135")));
    }
}
