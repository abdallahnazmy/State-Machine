package com.states.EmployeeStatesMS.repo;

import com.states.EmployeeStatesMS.entity.Contract;
import com.states.EmployeeStatesMS.enums.ContractType;
import com.states.EmployeeStatesMS.repository.ContractRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class ContractRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ContractRepository contractRepository;


    @Test
    @DisplayName("Insert contract test")
    public void insertContract() {
        Contract mockedContract = new Contract();
        mockedContract.setContractType(ContractType.FULL_TIME);
        mockedContract.setSalary(1000);

        Contract c = contractRepository.save(mockedContract);
        assertEquals(c.getId(), mockedContract.getId());
    }

    @Test
    @DisplayName("Get contract by id")
    void getContractTest() {
        Contract mockedContract = new Contract();
        mockedContract.setSalary(5000);
        mockedContract.setContractType(ContractType.FREELANCE);
        testEntityManager.persist(mockedContract);

        Contract found = contractRepository.findById(mockedContract.getId()).get();

        assertEquals(found.getId(), mockedContract.getId());
    }
}
