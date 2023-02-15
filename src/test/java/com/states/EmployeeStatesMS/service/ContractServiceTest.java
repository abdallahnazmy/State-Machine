package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Contract;
import com.states.EmployeeStatesMS.enums.ContractType;
import com.states.EmployeeStatesMS.repository.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractSerciceImpl contractService;

    private Contract fullTime;

    @BeforeEach
    @DisplayName("Initializing mock objects before running tests...")
    void init() {
        fullTime = new Contract();
        fullTime.setId(1L);
        fullTime.setContractType(ContractType.FULL_TIME);
        fullTime.setSalary(5000);
    }

    @Test
    @DisplayName("Add contract")
    void addContractTest() throws Exception {
        when(contractRepository.save(any(Contract.class))).thenReturn(fullTime);

        Contract newContract = contractService.addContract(fullTime);


        assertThat(newContract.getId()).isEqualTo(1L);
        assertThat(newContract.getContractType()).isEqualTo(ContractType.FULL_TIME);
        assertThat(newContract.getSalary()).isEqualTo(5000);
        assertNotNull(newContract.getCreationDate());
    }

    @Test
    @DisplayName("Get contract by id")
    void getContractTest() throws Exception {
        when(contractRepository.findById(anyLong())).thenReturn(Optional.of(fullTime));

        Contract found = contractService.getContract(1L).get();

        assertNotNull(found);
        assertThat(found.getId()).isEqualTo(1L);
        assertThat(found.getContractType()).isEqualTo(ContractType.FULL_TIME);
        assertThat(found.getSalary()).isEqualTo(5000);
        assertNotNull(found.getCreationDate());
    }
}
