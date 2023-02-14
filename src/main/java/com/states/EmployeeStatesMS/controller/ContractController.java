package com.states.EmployeeStatesMS.controller;

import com.states.EmployeeStatesMS.entity.Contract;
import com.states.EmployeeStatesMS.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) throws Exception {
        return new ResponseEntity<Contract>(contractService.addContract(contract), HttpStatus.CREATED);
    }
}
