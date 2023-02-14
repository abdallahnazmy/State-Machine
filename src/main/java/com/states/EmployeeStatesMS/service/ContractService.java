package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Contract;

public interface ContractService {
     Contract addContract(Contract contract) throws Exception;

     Contract getContract(Long id);
}
