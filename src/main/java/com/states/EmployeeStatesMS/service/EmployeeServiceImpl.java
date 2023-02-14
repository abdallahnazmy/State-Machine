package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.StateEvent;
import com.states.EmployeeStatesMS.repository.EmployeeRepository;
import com.states.EmployeeStatesMS.trigger.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
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
    public Employee changeEmployeeState(StateEvent stateEvent, Long id) throws Exception {

        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new Exception(String.format("No employee found for id %d ", id));
        }
        EventTrigger eventTrigger;
        switch (stateEvent) {
            case BEGIN_CHECK:
                eventTrigger = new BeginCheckEvent();
                break;
            case APPROVE:
                eventTrigger = new ApproveEvent();
                break;
            case UNAPPROVE:
                eventTrigger = new UnapproveEvent();
                break;
            case ACTIVATE:
                eventTrigger = new ActivateEvent();
                break;
            default:
                throw new UnsupportedOperationException(String.format("Invalid event %s", stateEvent));
        }
        eventTrigger.execute(employee.get());
        return employeeRepository.save(employee.get());
    }
}
