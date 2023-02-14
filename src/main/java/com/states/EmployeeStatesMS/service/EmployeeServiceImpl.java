package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.repository.EmployeeRepository;
import com.states.EmployeeStatesMS.trigger.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ContractService contractService;

    @Override
    public Employee addEmployee(Employee employee) throws Exception {
        if (!employee.validateEmployee()) {
            throw new Exception("Employee data is incomplete!");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new Exception(String.format("No employee found for id %d ", id));
        }
        return employee.get();
    }

    @Override
    public Employee updateEmployeeState(EventTrigger eventTrigger, Long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new Exception(String.format("No employee found for id %d ", id));
        }
        if (eventTrigger == null) {
            throw new Exception("Event type came from the request as null");
        }
        eventTrigger.changeState(employee.get());
        return employeeRepository.save(employee.get());
    }
}
