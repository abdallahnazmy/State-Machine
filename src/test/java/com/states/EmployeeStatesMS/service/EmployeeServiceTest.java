package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Contract;
import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.ContractType;
import com.states.EmployeeStatesMS.enums.EmployeeState;
import com.states.EmployeeStatesMS.repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.states.EmployeeStatesMS.trigger.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ContractSerciceImpl contractSercice;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee austin;

    private Employee beginCheckEmployee;
    private Employee approveEmployee;
    private Employee unapproveEmployee;
    private Employee activateEmployee;
    private Contract contract;

    @BeforeEach
    @DisplayName("Initializing mock objects before running tests...")
    void init() {
        contract = new Contract();
        contract.setId(1L);
        contract.setContractType(ContractType.FULL_TIME);
        contract.setSalary(2700);

        austin = new Employee();
        austin.setId(1L);
        austin.setFirstName("Dave");
        austin.setLastName("Austin");
        austin.setAge(26);
        austin.setNumber("01078824135");
        austin.setStartDate(LocalDate.now());
        austin.setContract(contract);

        beginCheckEmployee = new Employee();
        beginCheckEmployee.setId(2L);
        beginCheckEmployee.setEmployeeState(EmployeeState.ADDED);

        approveEmployee = new Employee();
        approveEmployee.setId(3L);
        approveEmployee.setEmployeeState(EmployeeState.IN_CHECK);

        unapproveEmployee = new Employee();
        unapproveEmployee.setId(4L);
        unapproveEmployee.setEmployeeState(EmployeeState.APPROVED);

        activateEmployee = new Employee();
        activateEmployee.setId(5L);
        activateEmployee.setEmployeeState(EmployeeState.APPROVED);
    }

    @Test
    @DisplayName("Add employee")
    void addEmployee() throws Exception {
        when(employeeRepository.save(any(Employee.class))).thenReturn(austin);
        when(contractSercice.getContract(anyLong())).thenReturn(Optional.of(contract));
        Employee employee = employeeService.addEmployee(austin);

        assertNotNull(employee);
        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getNumber()).isEqualTo("01078824135");
        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.ADDED);
        assertThat(employee.getContract().getId()).isEqualTo(contract.getId());
    }

    @Test
    @DisplayName("Get employee by id")
    void getEmployee() throws Exception {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(austin));

        Employee found = employeeService.getEmployee(1L);
        assertNotNull(found);
        assertThat(found.getId()).isEqualTo(1L);
        assertThat(found.getNumber()).isEqualTo("01078824135");
        assertThat(found.getEmployeeState()).isEqualTo(EmployeeState.ADDED);
        assertThat(found.getContract().getId()).isEqualTo(contract.getId());
    }

    @Test
    @DisplayName("Update employee event BeginCheck")
    void updateEmployeeBeginCheckTest() throws Exception {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(beginCheckEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(beginCheckEmployee);

        Employee employee = employeeService.getEmployee(2L);

        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.ADDED);

        employeeService.updateEmployeeState(new BeginCheckTrigger(), 2L);
        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.IN_CHECK);

    }

    @Test
    @DisplayName("Update employee event Approve")
    void updateEmployeeApproveTest() throws Exception {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(approveEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(approveEmployee);

        Employee employee = employeeService.getEmployee(3L);

        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.IN_CHECK);

        employeeService.updateEmployeeState(new ApproveTrigger(), 3L);
        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.APPROVED);

    }

    @Test
    @DisplayName("Update employee event Unapprove")
    void updateEmployeeUnapproveTest() throws Exception {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(unapproveEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(unapproveEmployee);

        Employee employee = employeeService.getEmployee(4L);

        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.APPROVED);

        employeeService.updateEmployeeState(new UnapproveTrigger(), 4L);
        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.IN_CHECK);

    }

    @Test
    @DisplayName("Update employee event Activate")
    void updateEmployeeActivateTest() throws Exception {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(activateEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(activateEmployee);

        Employee employee = employeeService.getEmployee(5L);

        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.APPROVED);

        employeeService.updateEmployeeState(new ActivateTrigger(), 5L);
        assertThat(employee.getEmployeeState()).isEqualTo(EmployeeState.ACTIVE);

    }
}
