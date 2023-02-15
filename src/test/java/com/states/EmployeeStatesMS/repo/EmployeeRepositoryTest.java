package com.states.EmployeeStatesMS.repo;

import com.states.EmployeeStatesMS.entity.Contract;
import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.ContractType;
import com.states.EmployeeStatesMS.enums.EmployeeState;
import com.states.EmployeeStatesMS.repository.ContractRepository;
import com.states.EmployeeStatesMS.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Test
    @DisplayName("Add employee")
    void addEmployeeTest() {
        Contract mockedContract = new Contract();
        mockedContract.setContractType(ContractType.FULL_TIME);
        mockedContract.setSalary(1000);

        contractRepository.save(mockedContract);

        Employee mockedEmployee = new Employee();
        mockedEmployee.setFirstName("Steve");
        mockedEmployee.setLastName("Jobs");
        mockedEmployee.setAge(20);
        mockedEmployee.setNumber("01235541265");
        mockedEmployee.setStartDate(LocalDate.now());

        Contract foundContract = contractRepository.findById(mockedContract.getId()).get();

        mockedEmployee.setContract(foundContract);

        Employee savedEmployee = employeeRepository.save(mockedEmployee);

        assertEquals(savedEmployee.getEmployeeState(), EmployeeState.ADDED);
        assertEquals(savedEmployee.getId(), mockedEmployee.getId());
        assertEquals(savedEmployee.getContract().getId(), mockedContract.getId());
    }

    @Test
    @DisplayName("Get employee by id")
    void getEmployee() {
        Contract mockedContract = new Contract();
        mockedContract.setContractType(ContractType.FULL_TIME);
        mockedContract.setSalary(1000);

        contractRepository.save(mockedContract);

        Employee mockedEmployee = new Employee();
        mockedEmployee.setFirstName("Steve");
        mockedEmployee.setLastName("Jobs");
        mockedEmployee.setAge(20);
        mockedEmployee.setNumber("01235541265");
        mockedEmployee.setStartDate(LocalDate.now());
        mockedEmployee.setContract(contractRepository.findById(mockedContract.getId()).get());

        testEntityManager.persist(mockedEmployee);

        Employee found = employeeRepository.findById(mockedEmployee.getId()).get();
        assertEquals(found.getId(), mockedEmployee.getId());
    }
}
