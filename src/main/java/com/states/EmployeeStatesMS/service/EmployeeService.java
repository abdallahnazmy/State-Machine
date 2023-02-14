package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.Event;
public interface EmployeeService {

    Employee addEmployee(Employee employee) throws Exception;

    Employee getEmployee(Long id) throws Exception;

    Employee updateEmployeeState(Event event, Long id) throws Exception;
}
