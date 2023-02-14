package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.Event;
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
    public Employee updateEmployeeState(Event event, Long id) throws Exception {

        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new Exception(String.format("No employee found for id %d ", id));
        }
        EventTrigger eventTrigger;
        switch (event) {
            case BEGIN_CHECK:
                eventTrigger = new BeginCheckTrigger();
                break;
            case APPROVE:
                eventTrigger = new ApproveTrigger();
                break;
            case UNAPPROVE:
                eventTrigger = new UnapproveTrigger();
                break;
            case ACTIVATE:
                eventTrigger = new ActivateTrigger();
                break;
            default:
                throw new UnsupportedOperationException(String.format("Invalid event %s", event));
        }
        eventTrigger.execute(employee.get());
        return employeeRepository.save(employee.get());
    }
}
