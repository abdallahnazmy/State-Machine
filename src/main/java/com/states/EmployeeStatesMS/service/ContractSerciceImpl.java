package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Contract;
import com.states.EmployeeStatesMS.repository.ContractRepository;
import com.states.EmployeeStatesMS.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ContractSerciceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Contract addContract(Contract contract) throws Exception {
        if (!contract.validateContract()) {
            throw new Exception("Contract data is incomplete");
        }
        return contractRepository.save(contract);
    }

    @Override
    public Optional<Contract> getContract(Long id) throws Exception {
        Optional<Contract> contract = contractRepository.findById(id);
        if (!contract.isPresent()) {
            throw new Exception(String.format("No contract found for id %d", id));
        }
        return contractRepository.findById(id);
    }
}
