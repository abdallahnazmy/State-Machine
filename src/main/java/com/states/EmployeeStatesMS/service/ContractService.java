package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Contract;
import java.util.Optional;

public interface ContractService {
     Contract addContract(Contract contract) throws Exception;

     Optional<Contract> getContract(Long id) throws Exception;
}
