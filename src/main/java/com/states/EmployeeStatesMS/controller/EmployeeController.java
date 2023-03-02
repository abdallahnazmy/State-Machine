package com.states.EmployeeStatesMS.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.states.EmployeeStatesMS.model.TriggerRequest;
import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable @NumberFormat Long id) throws Exception {
        return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws Exception {
        return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployeeState(@RequestBody TriggerRequest triggerRequest) throws Exception {
        return new ResponseEntity<Employee>(employeeService.updateEmployeeState(triggerRequest.getEvent()
                , triggerRequest.getId())
                , HttpStatus.OK);

    }
}
